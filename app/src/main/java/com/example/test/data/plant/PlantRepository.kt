package com.example.test.data.plant

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.test.api.ApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PlantRepository @Inject constructor(
    private val service: ApiService
) {

    fun getSearchPlant(area:String): Flow<PagingData<PlantData>> {
        return Pager(
            config = PagingConfig(
                enablePlaceholders = false,
                pageSize = 20
            ),
            pagingSourceFactory = { PlantPagingSource(service,area) }
        ).flow
    }

    suspend fun getPlant(area: String) = service.getPlant(q = area).data.results[0]
}
