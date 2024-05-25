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




class AlbumViewModel : ViewModel() {

    private var albumDetailLiveData = MutableLiveData<Album>()
    // Private MutableLiveData for the creation status
    private val _albumCreationStatus = MutableLiveData<Boolean>()
    val albumCreationStatus: LiveData<Boolean>
        get() = _albumCreationStatus

    fun getAlbum(id: String) {
        RetrofitInstance.api.getAlbum(id).enqueue(object : Callback<Album> {
            override fun onResponse(call: Call<Album>, response: Response<Album>) {
                if (response.isSuccessful) {
                    val album: Album? = response.body()
                    if (album != null) {
                        albumDetailLiveData.value = album
                    }
                } else {
                    // Manejar caso de respuesta no exitosa
                }
            }

            override fun onFailure(call: Call<Album>, t: Throwable) {
                Log.e("AlbumDetailFragment", t.message.toString())
            }
        })
    }


    private val _albumCreateResult = MutableLiveData<Result<Album>>()
    val albumCreateResult: LiveData<Result<Album>> get() = _albumCreateResult

    fun createAlbum(album: Album) {
        RetrofitInstance.api.postAlbum(album).enqueue(object : Callback<Album> {
            override fun onResponse(call: Call<Album>, response: Response<Album>) {
                if (response.isSuccessful) {
                    _albumCreateResult.postValue(Result.success(response.body()!!))
                } else {
                    _albumCreateResult.postValue(Result.failure(Exception("Error: ${response.code()} ${response.message()}")))
                }
            }

            override fun onFailure(call: Call<Album>, t: Throwable) {
                _albumCreateResult.postValue(Result.failure(t))
            }
        })
    }



    fun observeAlbumLiveData(): LiveData<Album> {
        return albumDetailLiveData
    }
}

//class AlbumViewModel(): ViewModel() {
//
//    private var albumDetailLiveData = MutableLiveData<Album>()
//
//    fun getAlbum(id: String){
//
//        RetrofitInstance.api.getAlbum(id).enqueue(object : Callback<Album> {
//            override fun onResponse(call: Call<Album>, response: Response<Album>) {
//                if(response.body() != null)
//                {
//                    val album : Album = response.body()!!
//                    albumDetailLiveData.value = album
//                }else
//                {
//                    return
//                }
//            }
//
//            override fun onFailure(call: Call<Album>, t: Throwable) {
//                Log.d("AlbumDetailFragment", t.message.toString())
//            }
//        })
//    }
//
//    fun observeAlbumLiveData(): LiveData<Album> {
//        return albumDetailLiveData
//    }
//}