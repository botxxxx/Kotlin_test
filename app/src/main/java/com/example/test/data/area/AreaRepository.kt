package com.example.test.data.area

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.test.api.ApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AreaRepository @Inject constructor(
    private val service: ApiService
) {

    fun getSearchArea(): Flow<PagingData<AreaData>> {
        return Pager(
            config = PagingConfig(
                enablePlaceholders = false,
                pageSize = 20
            ),
            pagingSourceFactory = { AreaPagingSource(service) }
        ).flow
    }
}
