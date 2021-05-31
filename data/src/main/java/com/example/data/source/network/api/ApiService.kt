package com.example.data.source.network.api

import com.example.data.source.network.response.LaporanRemote
import com.example.data.source.network.response.UserRemote
import com.google.gson.internal.LinkedHashTreeMap
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("users")
    suspend fun getUsers() : LinkedHashTreeMap<String, Any>

    @POST("users")
    suspend fun registerUser() : UserRemote

    @POST("laporans")
    suspend fun sendLaporan() : LaporanRemote



}