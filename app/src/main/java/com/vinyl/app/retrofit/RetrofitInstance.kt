package com.vinyl.app.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val api: AlbumApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://localhost:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AlbumApi::class.java)
    }
}