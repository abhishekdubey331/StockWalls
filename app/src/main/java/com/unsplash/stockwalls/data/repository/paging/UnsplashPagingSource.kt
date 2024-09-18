package com.unsplash.stockwalls.data.repository.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.unsplash.stockwalls.api.UnsplashApi
import com.unsplash.stockwalls.data.model.UnsplashPhotoItemDto

class UnsplashPagingSource(
    private val apiService: UnsplashApi
) : PagingSource<Int, UnsplashPhotoItemDto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UnsplashPhotoItemDto> {
        return try {
            val page = params.key ?: 1
            val response = apiService.getPhotoList(pageNo = page)
            LoadResult.Page(
                data = response,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, UnsplashPhotoItemDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
