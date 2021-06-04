package com.example.domain.usecase

import com.example.domain.model.Laporan
import com.example.domain.model.Login
import com.example.domain.model.Reauth
import com.example.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UseCase {

    suspend fun login(user : User) : Login?

    suspend fun getUsers(token : String) : String

    suspend fun createLaporan(token : String, laporan: Laporan) : Int

    suspend fun reAuth(refreshToken: String) : Reauth?

    suspend fun getUserLaporans(token : String) : List<Laporan>?

    suspend fun createUser(user : User) : Int

    suspend fun getAllLaporan() : List<ArrayList<Double>>?

    suspend fun getAllLaporanList() :  List<Laporan>?

    suspend fun modifyLaporanStatus(laporan_id : String, status : String) : Int

    suspend fun testToken(token: String) : Int

}