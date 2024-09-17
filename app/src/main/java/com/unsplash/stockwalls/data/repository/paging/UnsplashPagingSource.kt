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
            // Start page from 1 if undefined
            val page = params.key ?: 1
            // Make API request
            val response = apiService.getPhotoList(pageNo = page)

            // Return the data and information on how to load the next/previous page
            LoadResult.Page(
                data = response,
                prevKey = if (page == 1) null else page - 1, // No previous page if it's the first
                nextKey = if (response.isEmpty()) null else page + 1 // Null if no more data
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, UnsplashPhotoItemDto>): Int? {
        // This defines the key used to reset the pagination
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
