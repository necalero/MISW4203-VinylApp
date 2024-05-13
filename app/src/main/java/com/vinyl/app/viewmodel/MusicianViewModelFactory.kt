package com.vinyl.app.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MusicianViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MusicianViewModel::class.java)) {
            return MusicianViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
