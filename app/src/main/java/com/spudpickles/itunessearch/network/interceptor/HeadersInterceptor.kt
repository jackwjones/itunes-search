package com.spudpickles.itunessearch.network.interceptor

import com.spudpickles.itunessearch.network.NetworkConstants
import com.spudpickles.itunessearch.network.NetworkHeaders
import okhttp3.Interceptor
import okhttp3.Response

class HeadersInterceptor(private val headers: NetworkHeaders) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader(NetworkConstants.HEADER_CONTENT_TYPE, headers.contentType)
        return chain.proceed(request.build())
    }

}
