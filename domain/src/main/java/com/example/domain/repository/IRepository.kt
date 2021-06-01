package com.example.domain.repository

import com.example.domain.model.Laporan
import com.example.domain.model.Login
import com.example.domain.model.User

interface IRepository {

    suspend fun login(user : User) : Login?

    suspend fun getUsers(token : String) : String

    suspend fun createLaporan(token : String, laporan: Laporan) : Boolean

}