package com.example.nambatestapp.data.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.nambatestapp.BuildConfig.KEY
import com.example.nambatestapp.data.remote.api_service.PicturesApiService
import com.example.nambatestapp.data.remote.paging_src.PicturesPagingSource
import com.example.nambatestapp.domain.model.PicturesModel
import com.example.nambatestapp.domain.repository.PicturesRepository
import kotlinx.coroutines.flow.Flow

class PicturesRepositoryImpl(
    private val apiService: PicturesApiService
): PicturesRepository {

    override fun getPictures(search: String?): Flow<PagingData<PicturesModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                initialLoadSize = 20
            ),
            pagingSourceFactory = {
                PicturesPagingSource(
                    key = KEY,
                    search = search,
                    apiService = apiService,
                )
            }
        ).flow
    }
}