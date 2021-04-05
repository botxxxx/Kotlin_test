package com.example.test.viewmodels

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.test.data.area.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class DisplayAreasViewModel @Inject constructor(
    private val areaRepository: AreaRepository
) : ViewModel() {

    fun getResult(): Flow<PagingData<AreaData>> {
        return areaRepository.getSearchArea().cachedIn(viewModelScope)
    }
}