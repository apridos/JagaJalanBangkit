package com.example.data.source

import android.util.Log
import com.example.data.source.network.api.ApiService
import com.example.data.source.network.response.LoginRequest
import com.example.data.source.network.response.LoginResponse
import com.example.data.source.network.response.ReauthResponse
import com.example.domain.model.Laporan
import com.example.domain.model.User
import com.google.gson.Gson
import org.json.JSONObject
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Named


class RemoteDataSource @Inject constructor(
    @Named("apiService")private val apiService: ApiService,
    @Named("authService")private val authService: ApiService,
    @Named("reAuthService")private val reAuthService: ApiService) {

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
            apiService.getUsers(token)
        }catch (e : Exception){
            e.toString()
        }

    }

    suspend fun createLaporan(token : String, laporan: Laporan): Int {
        return try{
            apiService.createLaporan(token, laporan).code()
        }catch (e : Exception){
            Log.d("tag", e.toString())
            401
        }
    }

    suspend fun reAuth(refreshToken: String) : ReauthResponse? {
        return try {
            reAuthService.reLogin(refreshToken).body()
        }catch (e : Exception){
            null
        }
    }


    suspend fun getUserLaporans(token : String): List<Laporan>?{
        return try {
            val listLaporan = arrayListOf<Laporan>()
            apiService.getUserLaporans(token).laporanList.map {
                val jsonObject = JSONObject(Gson().toJson(it, Map::class.java))
                val keys = jsonObject.keys()
                val laporan = Laporan()
                while(keys.hasNext()){
                    val key = keys.next()
                    when(key){
                        "alamat" -> laporan.alamat = jsonObject.getString(key)
                        "idLaporan" -> laporan.idLaporan = jsonObject.getString(key)
                        "kondisi_kerusakan" -> laporan.kondisi_kerusakan = jsonObject.getString(key)
                        "deskripsi" -> laporan.deskripsi = jsonObject.getString(key)
                        "latitude" -> laporan.latitude = jsonObject.getDouble(key)
                        "longitude" -> laporan.longitude = jsonObject.getDouble(key)
                        "id" -> laporan.idLaporan = jsonObject.getString(key)
                        "predicted_foto" -> laporan.foto = jsonObject.getString(key)
                        "status" -> laporan.status = jsonObject.getString(key)
                    }

                }
                listLaporan.add(laporan)
            }
            listLaporan
        }catch (e : Exception){
            Log.d("exception", e.toString())
            null
        }
    }

    suspend fun createUser(user : User) : Int{
        return apiService.createUser(user).code()
    }

    suspend fun getAllLaporan() : List<ArrayList<Double>>?{
        return try {
            val listCoordinate = arrayListOf(arrayListOf<Double>())
            val response = apiService.getAllLaporan()
            for (item in response.indices){
                listCoordinate.add(arrayListOf(response[item].get("longitude").toString().toDouble(), response[item].get("latitude").toString().toDouble()))
            }
            listCoordinate
        }catch (e : Exception){
            null
        }
    }

    suspend fun getAllLaporanList() : List<Laporan>? {
        return try {
            val listLaporan = arrayListOf<Laporan>()
            apiService.getAllLaporanList().map {
                val jsonObject = JSONObject(Gson().toJson(it, Map::class.java))
                val keys = jsonObject.keys()
                val laporan = Laporan()
                while(keys.hasNext()){
                    val key = keys.next()
                    Log.d("keys" , key.toString())

                    when(key){
                        "alamat" -> laporan.alamat = jsonObject.getString(key)
                        "idLaporan" -> laporan.idLaporan = jsonObject.getString(key)
                        "kondisi_kerusakan" -> laporan.kondisi_kerusakan = jsonObject.getString(key)
                        "deskripsi" -> laporan.deskripsi = jsonObject.getString(key)
                        "latitude" -> laporan.latitude = jsonObject.getDouble(key)
                        "longitude" -> laporan.longitude = jsonObject.getDouble(key)
                        "id" -> laporan.idLaporan = jsonObject.getString(key)
                        "predicted_foto" -> laporan.foto = jsonObject.getString(key)
                        "status" -> laporan.status = jsonObject.getString(key)
                    }
                    if (key == "predicted_foto"){
                        Log.d("link", jsonObject.getString("predicted_foto"))
                    }
                }
                listLaporan.add(laporan)
            }
            listLaporan
        }catch (e : Exception){
            Log.d("nih", e.toString())
            null
        }
    }

    suspend fun modifyLaporanStatus(laporan_id : String, status : String) : Int{
        return try{
            Log.d("GOOD", "GOODD")
            apiService.modifyLaporanStatus(laporan_id, status).code()
        }catch (e : Exception){
            400
        }
    }

}
