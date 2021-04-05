package com.example.test.data.area

import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadResult.Page
import com.example.test.api.ApiService

private const val UNSPLASH_STARTING_PAGE_INDEX = 1

class AreaPagingSource(
    private val service: ApiService
) : PagingSource<Int, AreaData>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AreaData> {
        val page = params.key ?: UNSPLASH_STARTING_PAGE_INDEX
        return try {
            val response = service.getArea()
            val data = response.data.results
            Page(
                data = data,
                prevKey = null,
                nextKey = null
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}
