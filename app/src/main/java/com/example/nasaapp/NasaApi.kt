package com.example.nasaapp

import com.example.nasaapp.model.NasaResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NasaApi {

    @GET("EPIC/api/natural/date/{date}")
    fun getPhotos(@Path("date") date: String,
                  @Query("api_key") apikey: String = "3NfsS6QwSV8FRJbeCG0OU8KfheYm64c7Ct47kR1S"): Call<NasaResponse>
}