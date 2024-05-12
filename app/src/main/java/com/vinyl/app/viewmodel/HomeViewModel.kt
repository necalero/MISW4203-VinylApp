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

class HomeViewModel : ViewModel() {

    private val albumsLiveData = MutableLiveData<List<Album>>()

    fun getAlbums() {
        RetrofitInstance.api.getAlbums().enqueue(object : Callback<List<Album>> {
            override fun onResponse(call: Call<List<Album>>, response: Response<List<Album>>) {
                if (response.isSuccessful) {
                    albumsLiveData.value = response.body() ?: emptyList()
                } else {
                    handleFailure(response.message())
                }
            }

            override fun onFailure(call: Call<List<Album>>, t: Throwable) {
                handleFailure(t.message.toString())
            }
        })
    }

    private fun handleFailure(message: String?) {
        Log.d("HomeFragment", message ?: "Unknown error")
    }

    fun observeAlbumsLiveData(): LiveData<List<Album>> = albumsLiveData
}
