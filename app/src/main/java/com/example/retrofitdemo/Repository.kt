package com.example.retrofitdemo

import com.example.retrofitdemo.network.ApiService

class Repository(private val apiService: ApiService) {
    suspend fun getCharacters(page: String) = apiService.fetchCharacters(page)
}