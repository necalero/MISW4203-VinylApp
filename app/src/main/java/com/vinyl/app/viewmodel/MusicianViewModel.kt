package com.vinyl.app.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vinyl.app.pojo.Musician
import com.vinyl.app.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.content.Context
import com.vinyl.app.retrofit.CacheManager



class MusicianViewModel(private val context: Context): ViewModel() {

    private var musiciansLiveData = MutableLiveData<List<Musician>>()
    private val cacheManager = CacheManager.getInstance(context)

    //private var musicianDetailLiveData = MutableLiveData<Musician>()

    fun getMusicians(){
        val cachedMusicians = cacheManager.getMusicians()
        if (cachedMusicians.isNotEmpty()) {
            musiciansLiveData.value = cachedMusicians
        } else {
            fetchMusiciansFromNetwork()
        }
    }

    private fun fetchMusiciansFromNetwork() {
        RetrofitInstance.api.getMusicians().enqueue(object : Callback<List<Musician>> {
            override fun onResponse(call: Call<List<Musician>>, response: Response<List<Musician>>) {
                if (response.isSuccessful) {
                    val musicians : List<Musician> = response.body()!!
                    cacheManager.addMusicians(musicians)
                    musiciansLiveData.value = musicians
                } else {
                    return
                }
            }

            override fun onFailure(call: Call<List<Musician>>, t: Throwable) {
                Log.d("MusicianDetailFragment", t.message.toString())
            }
        })
    }
    fun observeMusiciansLiveData(): LiveData<List<Musician>> {
        return musiciansLiveData
    }

    /*
    fun getMusician(id: String){

        RetrofitInstance.api.getMusician(id).enqueue(object : Callback<Musician> {
            override fun onResponse(call: Call<Musician>, response: Response<Musician>) {
                if(response.body() != null)
                {
                    val musician : Musician = response.body()!!
                    musicianDetailLiveData.value = musician
                }else
                {
                    return
                }
            }

            override fun onFailure(call: Call<Musician>, t: Throwable) {
                Log.d("MusicianDetailFragment", t.message.toString())
            }
        })
    }
    fun observeMusicianLiveData(): LiveData<Musician> {
        return musicianDetailLiveData
    }*/
}