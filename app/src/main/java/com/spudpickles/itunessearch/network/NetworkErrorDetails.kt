package com.spudpickles.itunessearch.network

data class NetworkErrorDetails (
    val code: String,
    val message: String,
    val data: Any
)
