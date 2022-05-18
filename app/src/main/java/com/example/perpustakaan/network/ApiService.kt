package com.example.perpustakaan.network

import com.example.perpustakaan.model.GetAllBukuResponseItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("buku")
    fun getAllBuku() : Call<List<GetAllBukuResponseItem>>
}