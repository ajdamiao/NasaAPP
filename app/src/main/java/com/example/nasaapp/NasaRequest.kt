package com.example.nasaapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NasaRequest {
    private val BASE_URL = "https://api.nasa.gov/"

    fun makeRequest(): NasaApi {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NasaApi::class.java)
    }
}