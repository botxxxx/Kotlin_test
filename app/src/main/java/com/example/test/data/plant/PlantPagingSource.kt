package com.example.test.data.plant

import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadResult.Page
import com.example.test.api.ApiService

private const val UNSPLASH_STARTING_PAGE_INDEX = 1

class PlantPagingSource(
    private val service: ApiService,
    private val area: String
) : PagingSource<Int, PlantData>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PlantData> {
        val page = params.key ?: UNSPLASH_STARTING_PAGE_INDEX
        return try {
            val response = service.getPlant(q = area)
            val data = response.data.results
            Page(
                data = data,
                prevKey = null,
                nextKey =  null
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}
