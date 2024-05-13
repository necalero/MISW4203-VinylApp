package com.vinyl.app.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vinyl.app.pojo.Musician
import com.vinyl.app.retrofit.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import android.content.Context
import com.vinyl.app.retrofit.CacheManager
import java.io.IOException



class MusicianViewModel(private val context: Context) : ViewModel() {

    private val cacheManager = CacheManager.getInstance(context)
    private var musicianDetailLiveData = MutableLiveData<Musician>()

    private val _musiciansLoadState = MutableLiveData<LoadState>()
    val musiciansLoadState: LiveData<LoadState> = _musiciansLoadState
    private val _musiciansLiveData = MutableLiveData<List<Musician>>()
    val musiciansLiveData: LiveData<List<Musician>> = _musiciansLiveData

    fun getMusicians() {
        val cachedMusicians = cacheManager.getMusicians()
        if (cachedMusicians.isNotEmpty()) {
            _musiciansLiveData.value= cachedMusicians
        } else {
            fetchMusiciansFromNetwork()
        }
    }

    private fun fetchMusiciansFromNetwork() {
        viewModelScope.launch {
            try {
                _musiciansLoadState.value = LoadState.Loading
                val musicians = RetrofitInstance.api.getMusicians()
                _musiciansLiveData.value = musicians
                _musiciansLoadState.value = LoadState.Success
            } catch (e: IOException) {
                _musiciansLoadState.value = LoadState.Error
            } catch (e: HttpException) {
                _musiciansLoadState.value = LoadState.Error
            }
        }

    }

    fun observeMusiciansLiveData(): LiveData<List<Musician>> {
        return musiciansLiveData
    }

    enum class LoadState {
        Loading, Success, Error
    }
}