package com.vinyl.app.retrofit

import android.content.Context
import android.util.LruCache
import com.vinyl.app.pojo.Album
import com.vinyl.app.pojo.Musician
import com.vinyl.app.pojo.Collector

class CacheManager(context: Context) {

    companion object{
        private var instance: CacheManager? = null

        fun getInstance(context: Context): CacheManager {
            return instance ?: synchronized(this){
                instance ?: CacheManager(context).also { instance = it }
            }
        }
    }

    private val albums: MutableList<Album> = mutableListOf()

    fun addAlbums(newAlbums: List<Album>) {
        albums.clear()
        albums.addAll(newAlbums)
    }

    fun getAlbums(): List<Album>{
        return albums.toList()
    }

    private val musicians: MutableList<Musician> = mutableListOf()

    fun addMusicians(newMusicians: List<Musician>) {
        musicians.clear()
        musicians.addAll(newMusicians)
    }

    fun getMusicians(): List<Musician>{
        return musicians.toList()
    }

    private val collectors: MutableList<Collector> = mutableListOf()

    fun addCollectors(newCollectors: List<Collector>) {
        collectors.clear()
        collectors.addAll(newCollectors)
    }

    fun getCollectors(): List<Collector>{
        return collectors.toList()
    }

}