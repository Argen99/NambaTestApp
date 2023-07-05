package com.example.nambatestapp.data.remote.paging_src

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.nambatestapp.data.remote.api_service.PicturesApiService
import com.example.nambatestapp.data.remote.model.toModel
import com.example.nambatestapp.domain.model.PicturesModel
import java.io.IOException

class PicturesPagingSource(
    private val key: String,
    private val search: String?,
    private val apiService: PicturesApiService
) : PagingSource<Int, PicturesModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PicturesModel> {

        val page = params.key ?: 1

        return try {
            val response = apiService.getAllPictures(
                key = key,
                search = search,
                page = page,
                perPage = params.loadSize,
            )

            LoadResult.Page(
                data = response.hits.map { it.toModel() },
                nextKey = if (response.hits.size == params.loadSize) page.plus(1) else null,
                prevKey = page.minus(1)
            )
        } catch (io: IOException) {
            io.printStackTrace()
            LoadResult.Error(io)
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PicturesModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}