package com.example.nambatestapp.domain.repository

import androidx.paging.PagingData
import com.example.nambatestapp.domain.model.PicturesModel
import kotlinx.coroutines.flow.Flow

interface PicturesRepository {

    fun getPictures(search: String?): Flow<PagingData<PicturesModel>>
}