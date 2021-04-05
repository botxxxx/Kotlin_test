package com.example.test.viewmodels

import androidx.lifecycle.*
import androidx.paging.*
import com.example.test.data.area.*
import com.example.test.data.plant.PlantData
import com.example.test.data.plant.PlantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class AreaViewModel @Inject constructor(
    private val plantRepository: PlantRepository
) : ViewModel() {

    fun getResult(area:String): Flow<PagingData<PlantData>> {
        return plantRepository.getSearchPlant(area).cachedIn(viewModelScope)
    }
}