package com.vinyl.app.retrofit

import com.vinyl.app.pojo.Album
import com.vinyl.app.pojo.Collector
import com.vinyl.app.pojo.Musician
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface VinylApi {

    @GET("albums")
    fun getAlbums(): Call<List<Album>>

    @GET("musicians")
    suspend fun getMusicians(): List<Musician>

    @GET("musicians/{id}")
    fun getMusician(@Path("id") id:String):Call<Musician>

    @GET("albums/{id}")
    fun getAlbum(@Path("id") id:String):Call<Album>

    @GET("collectors")
    fun getCollectors(): Call<List<Collector>>

    @GET("collectors/{id}")
    fun getCollector(@Path("id") id:String):Call<Collector>

    @POST("albums")
    fun postAlbum(album: Album): Call<Album>

}