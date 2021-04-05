package com.example.test.data.plant

import com.google.gson.annotations.SerializedName

data class PlantData(
    val F_Name_Latin: String,
    val F_Name_En: String,
    @SerializedName("﻿F_Name_Ch")
    val F_Name_Ch: String,
    val F_Pic01_URL: String? = null,
    // 館內位置
    val F_Location: String? = null,
    val F_Geo: String? = null,
    // 別名
    val F_AlsoKnown: String? = null,
    // 世界分佈
    val F_Brief: String? = null,
    // 外觀
    val F_Feature: String? = null,
    // 科
    val F_Family: String? = null,
    // 屬
    val F_Genus: String? = null,
    // 用途
    @SerializedName("F_Function＆Application")
    val F_Function: String? = null,
    val F_Update: String? = null,
    val _id: Long? = null
)