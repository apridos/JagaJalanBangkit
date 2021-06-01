package com.example.data.source

import android.util.Log
import com.example.data.source.network.api.ApiService
import com.example.data.source.network.response.LoginRequest
import com.example.data.source.network.response.LoginResponse
import com.example.domain.model.Laporan
import java.lang.Exception
import java.util.function.LongFunction
import javax.inject.Inject
import javax.inject.Named


class RemoteDataSource @Inject constructor(
    @Named("apiService")private val apiService: ApiService,
    @Named("authService")private val authService: ApiService) {

    suspend fun login( email : String, password : String) : LoginResponse? {
        return try{

            authService.login("AIzaSyDPrUyahZHrP1p16yY4_vOi19i_P_JLHVs", LoginRequest(
                email = email,
                password = password,
            )).body()
        }catch (e : Exception){
            null
        }
    }

    suspend fun getUsers(token : String) : String{
        return try {
            Log.d("token", token)
            apiService.getUsers(token)
        }catch (e : Exception){
            Log.d("err", e.toString())
            e.toString()
        }

    }

    suspend fun createLaporan(token : String, laporan: Laporan): Boolean{
        return try{
            Log.d("token", token)
            if(apiService.createLaporan(token, laporan).body()?.idLaporan != null){
                true
            }else{
                false
            }
        }catch (e : Exception){
            Log.d("tag", e.toString())
            false
        }
    }

}

