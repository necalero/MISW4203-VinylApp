package com.vinyl.app.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vinyl.app.pojo.Album
import com.vinyl.app.pojo.Musician
import com.vinyl.app.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlbumViewModel(): ViewModel() {

    private var albumDetailLiveData = MutableLiveData<Album>()

    fun getAlbum(id: String){

        RetrofitInstance.api.getAlbum(id).enqueue(object : Callback<Album> {
            override fun onResponse(call: Call<Album>, response: Response<Album>) {
                if(response.body() != null)
                {
                    val album : Album = response.body()!!
                    albumDetailLiveData.value = album
                }else
                {
                    return
                }
            }

            override fun onFailure(call: Call<Album>, t: Throwable) {
                Log.d("AlbumDetailFragment", t.message.toString())
            }
        })
    }

    fun observeAlbumLiveData(): LiveData<Album> {
        return albumDetailLiveData
    }
}