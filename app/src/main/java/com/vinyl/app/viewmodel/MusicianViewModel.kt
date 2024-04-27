package com.vinyl.app.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vinyl.app.pojo.Musician
import com.vinyl.app.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MusicianViewModel {

    private var musiciansLiveData = MutableLiveData<List<Musician>>()

    private var musicianDetailLiveData = MutableLiveData<Musician>()

    fun getMusicians(){

        RetrofitInstance.api.getMusicians().enqueue(object : Callback<List<Musician>> {
            override fun onResponse(call: Call<List<Musician>>, response: Response<List<Musician>>) {
                if(response.body() != null)
                {
                    val musicians : List<Musician> = response.body()!!
                    musiciansLiveData.value = musicians
                }else
                {
                    return
                }
            }

            override fun onFailure(call: Call<List<Musician>>, t: Throwable) {
                Log.d("MusicianListFragment", t.message.toString())
            }
        })
    }

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

    fun observeMusiciansLiveData(): LiveData<List<Musician>> {
        return musiciansLiveData
    }

    fun observeMusicianLiveData(): LiveData<Musician> {
        return musicianDetailLiveData
    }
}