package com.example.test.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

interface ApiService {

    // 臺北市立動物園_館區簡介
    // https://data.taipei/#/dataset/detail?id=1ed45a8a-d26a-4a5f-b544-788a4071eea2
    @GET("5a0e5fbb-72f8-41c6-908e-2fb25eff9b8a")
    suspend fun getArea(
        @Query("scope") scope: String = "resourceAquire",
        @Query("q") q: String? = null,
        @Query("limit") limit: Int? = null,
        @Query("offset") offset: Int? = null
    ): AreaResponse

    // 臺北市立動物園_植物資料
    // https://data.taipei/#/dataset/detail?id=48c4d6a7-4b09-4d1f-9739-ee837d302bd1
    @GET("f18de02f-b6c9-47c0-8cda-50efad621c14")
    suspend fun getPlant(
        @Query("scope") scope: String = "resourceAquire",
        @Query("q") q: String? = null,
        @Query("limit") limit: Int? = null,
        @Query("offset") offset: Int? = null
    ): PlantResponse

    companion object {
        private const val BASE_URL = "https://data.taipei/api/v1/dataset/"
        fun create(): ApiService {
            val logger = HttpLoggingInterceptor().apply { level = Level.BASIC }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .connectTimeout(15, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build()
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }
}
