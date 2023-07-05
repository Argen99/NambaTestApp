package com.example.nambatestapp.data.di

import com.example.nambatestapp.BuildConfig.BASE_URL
import com.example.nambatestapp.data.remote.api_service.PicturesApiService
import com.example.nambatestapp.data.remote.repository.PicturesRepositoryImpl
import com.example.nambatestapp.domain.repository.PicturesRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val dataModule = module {

    singleOf(::PicturesRepositoryImpl) {
        bind<PicturesRepository>()
    }

    factoryOf(::provideOkHttpClient)
    factoryOf(::provideForecastApi)
    singleOf(::provideRetrofit)

}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
}

fun provideOkHttpClient(): OkHttpClient {
    val interceptor = HttpLoggingInterceptor()
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    return OkHttpClient().newBuilder()
        .addInterceptor(interceptor)
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .build()
}

fun provideForecastApi(retrofit: Retrofit): PicturesApiService {
    return retrofit.create(PicturesApiService::class.java)
}