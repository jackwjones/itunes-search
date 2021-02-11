package com.spudpickles.itunessearch.data.entities

data class ITunesItem(
    val artistName: String,
    val trackName: String,
    val trackPrice: Number,
    val releaseDateString: String,
    val primaryGenreName: String
)
