package com.vinyl.app.pojo

data class Album(
    val id: String,
    val cover: String,
    val description: String,
    val genre: String,
    val name: String,
    val recordLabel: String,
    val releaseDate: String,
    val tracks: List<Track> = emptyList()
)