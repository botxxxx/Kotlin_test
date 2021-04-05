package com.example.test.viewmodels

import androidx.lifecycle.*
import com.example.test.data.plant.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlantViewModel @Inject constructor(
    private val plantRepository: PlantRepository
) : ViewModel() {

    suspend fun getResult(area: String): PlantData {
        return plantRepository.getPlant(area)
    }
}