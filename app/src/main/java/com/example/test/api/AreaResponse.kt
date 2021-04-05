package com.example.test.api

import com.example.test.data.area.AreaData
import com.google.gson.annotations.SerializedName

data class AreaResponse(
    @SerializedName("result")
    val data: area_result
)

data class area_result(
    val count: Int,
    val results: List<AreaData>
)