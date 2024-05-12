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

class CollectorViewModel : ViewModel() {

    private val collectorsLiveData = MutableLiveData<List<Collector>>()
    private val collectorDetailLiveData = MutableLiveData<Collector>()

    fun getCollectors() {
        RetrofitInstance.api.getCollectors().enqueue(object : Callback<List<Collector>> {
            override fun onResponse(call: Call<List<Collector>>, response: Response<List<Collector>>) {
                if (response.isSuccessful) {
                    collectorsLiveData.value = response.body() ?: emptyList()
                } else {
                    handleFailure("CollectorListFragment", response.message())
                }
            }

            override fun onFailure(call: Call<List<Collector>>, t: Throwable) {
                handleFailure("CollectorListFragment", t.message.toString())
            }
        })
    }

    fun getCollector(id: String) {
        RetrofitInstance.api.getCollector(id).enqueue(object : Callback<Collector> {
            override fun onResponse(call: Call<Collector>, response: Response<Collector>) {
                if (response.isSuccessful) {
                    collectorDetailLiveData.value = response.body()
                } else {
                    handleFailure("CollectorDetailFragment", response.message())
                }
            }

            override fun onFailure(call: Call<Collector>, t: Throwable) {
                handleFailure("CollectorDetailFragment", t.message.toString())
            }
        })
    }

    private fun handleFailure(tag: String, message: String?) {
        Log.d(tag, message ?: "Unknown error")
    }

    fun observeCollectorsLiveData(): LiveData<List<Collector>> = collectorsLiveData

    fun observeCollectorLiveData(): LiveData<Collector> = collectorDetailLiveData
}
