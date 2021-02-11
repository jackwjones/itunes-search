package com.spudpickles.itunessearch.network.api

import com.spudpickles.itunessearch.network.model.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ITunesApiService {

    @GET("search")
    fun getTitles(
        @Query("term") searchTerm: String
    ): Call<SearchResponse>
}
