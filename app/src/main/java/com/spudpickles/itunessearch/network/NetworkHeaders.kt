package com.spudpickles.itunessearch.network

import com.spudpickles.itunessearch.BuildConfig

class NetworkHeaders {
    val version: String get() = BuildConfig.VERSION_NAME
    val contentType: String get() = NetworkConstants.CLIENT_CONTENT_TYPE_JSON_UTF8
}
