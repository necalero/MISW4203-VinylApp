package com.vinyl.app.retrofit

import com.vinyl.app.pojo.Album
import retrofit2.Call
import retrofit2.http.GET

interface AlbumApi {

    @GET("albums")
    fun getAlbums(): Call<List<Album>>
}