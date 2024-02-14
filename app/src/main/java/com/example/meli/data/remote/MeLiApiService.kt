package com.example.meli.data.remote

import com.example.meli.data.model.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MeLiApiService {

    @GET("sites/MLA/search?q=")
    fun searchProducts(
        @Query("q") query: String
    ): Call<SearchResponse>
}