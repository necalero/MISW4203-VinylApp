package com.vinyl.app.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vinyl.app.pojo.Album
import com.vinyl.app.retrofit.CacheManager
import com.vinyl.app.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(private val context: Context) : ViewModel() {

    private var albumsLiveData = MutableLiveData<List<Album>>()
    private val cacheManager = CacheManager.getInstance(context)

    fun getAlbums() {
        val cachedAlbums = cacheManager.getAlbums()
        if (cachedAlbums.isNotEmpty()) {
            albumsLiveData.value = cachedAlbums
        } else {
            fetchAlbumsFromNetwork()
        }
    }

    private fun fetchAlbumsFromNetwork() {
        RetrofitInstance.api.getAlbums().enqueue(object : Callback<List<Album>> {
            override fun onResponse(call: Call<List<Album>>, response: Response<List<Album>>) {
                if (response.isSuccessful) {
                    val albums: List<Album> = response.body()?.also { cacheManager.addAlbums(it) } ?: emptyList()
                    albumsLiveData.value = albums
                } else {
                    return
                }
            }

            override fun onFailure(call: Call<List<Album>>, t: Throwable) {
                Log.e("HomeFragment", t.message.toString())
            }
        })
    }

    fun observeAlbumsLiveData(): LiveData<List<Album>> {
        return albumsLiveData
    }
}
