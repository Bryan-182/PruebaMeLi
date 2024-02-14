package com.example.meli.data.repository

import com.example.meli.data.model.SearchResponse
import com.example.meli.data.remote.MeLiApiService
import retrofit2.Call

class ProductRepository(private val apiService: MeLiApiService) {

    fun searchProducts(query: String): Call<SearchResponse> {
        return apiService.searchProducts(query)
    }
}