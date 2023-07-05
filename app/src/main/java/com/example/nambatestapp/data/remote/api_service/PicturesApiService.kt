package com.example.nambatestapp.data.remote.api_service

import com.example.nambatestapp.data.remote.model.PicturesDto
import com.example.nambatestapp.data.remote.model.PicturesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PicturesApiService {

    @GET(".")
    suspend fun getAllPictures(
        @Query("key") key: String,
        @Query("q") search: String?,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): PicturesResponse<PicturesDto>
}