package com.example.test.api

import com.example.test.data.plant.PlantData
import com.google.gson.annotations.SerializedName

data class PlantResponse(
    @SerializedName("result")
    val data: plant_result
)

data class plant_result(
    val results: List<PlantData>
)