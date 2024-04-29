package com.vinyl.app.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vinyl.app.pojo.Album
import com.vinyl.app.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(): ViewModel() {

    private var albumsLiveData = MutableLiveData<List<Album>>()

    fun getAlbums(){
        //TODO: Implement retrofit logic to load album cards
        RetrofitInstance.api.getAlbums().enqueue(object : Callback<List<Album>> {
            override fun onResponse(call: Call<List<Album>>, response: Response<List<Album>>) {
                if(response.body() != null)
                {
                    val albums : List<Album> = response.body()!!
                    albumsLiveData.value = albums
                }else
                {
                    return
                }
            }

            override fun onFailure(call: Call<List<Album>>, t: Throwable) {
                Log.d("HomeFragment", t.message.toString())
            }
        })
    }

    fun observeAlbumsLiveData():LiveData<List<Album>>{
        return albumsLiveData
    }
}