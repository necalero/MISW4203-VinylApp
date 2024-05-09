package com.vinyl.app.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vinyl.app.pojo.Collector
import com.vinyl.app.pojo.Musician
import com.vinyl.app.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CollectorViewModel():ViewModel() {

    private var collectorsLiveData = MutableLiveData<List<Collector>>()

    private var collectorDetailLiveData = MutableLiveData<Collector>()

    fun getCollectors(){

        RetrofitInstance.api.getCollectors().enqueue(object : Callback<List<Collector>> {
            override fun onResponse(call: Call<List<Collector>>, response: Response<List<Collector>>) {
                if(response.body() != null)
                {
                    val collectors : List<Collector> = response.body()!!
                    collectorsLiveData.value = collectors
                }else
                {
                    return
                }
            }

            override fun onFailure(call: Call<List<Collector>>, t: Throwable) {
                Log.d("CollectorListFragment", t.message.toString())
            }
        })
    }

    fun getCollector(id: String){

        RetrofitInstance.api.getCollector(id).enqueue(object : Callback<Collector> {
            override fun onResponse(call: Call<Collector>, response: Response<Collector>) {
                if(response.body() != null)
                {
                    val collector : Collector = response.body()!!
                    collectorDetailLiveData.value = collector
                }else
                {
                    return
                }
            }

            override fun onFailure(call: Call<Collector>, t: Throwable) {
                Log.d("CollectorDetailFragment", t.message.toString())
            }
        })
    }

    fun observeCollectorsLiveData(): LiveData<List<Collector>> {
        return collectorsLiveData
    }

    fun observeCollectorLiveData(): LiveData<Collector> {
        return collectorDetailLiveData
    }
}