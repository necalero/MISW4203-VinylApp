package com.vinyl.app.pojo

data class Collector(
    val collectorAlbums: List<Album>,
    val comments: List<Comment>,
    val email: String,
    val favoritePerformers: List<Musician>,
    val id: Int,
    val name: String,
    val telephone: String
)