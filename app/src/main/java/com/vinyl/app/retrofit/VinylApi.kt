package com.vinyl.app.retrofit

import com.vinyl.app.pojo.Album
import com.vinyl.app.pojo.Musician
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface VinylApi {

    @GET("albums")
    fun getAlbums(): Call<List<Album>>

    @GET("musicians")
    fun getMusicians(): Call<List<Musician>>

    @GET("musicians/{id}")
    fun getMusician(@Path("id") id:String):Call<Musician>
}