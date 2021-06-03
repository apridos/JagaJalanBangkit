package com.example.data

import com.example.data.source.RemoteDataSource
import com.example.data.utils.DataMapper
import com.example.domain.model.Laporan
import com.example.domain.model.Login
import com.example.domain.model.Reauth
import com.example.domain.model.User
import com.example.domain.repository.IRepository
import java.lang.Exception
import javax.inject.Inject

class Repository @Inject constructor(private val remoteDataSource: RemoteDataSource) : IRepository{


    override suspend fun login(user: User): Login? {
        return remoteDataSource.login(user.email, user.password)?.let {
            DataMapper.mapLoginResponseToLogin(it)
        }
    }

    override suspend fun getUserLaporans(token : String): List<Laporan>? {
        return remoteDataSource.getUserLaporans(token)
    }

    override suspend fun getUsers(token : String): String {
        return remoteDataSource.getUsers(token)
    }

    override suspend fun createLaporan(token : String, laporan: Laporan): Int {
        return remoteDataSource.createLaporan(token, laporan)
    }

    override suspend fun reAuth(refreshToken: String): Reauth? {
        return try{
            remoteDataSource.reAuth(refreshToken)?.let { DataMapper.mapReauthResponseToReauth(it) }
        }catch (e : Exception) {
            null
        }
    }

    override suspend fun createUser(user: User): Int {
        return remoteDataSource.createUser(user)
    }

    override suspend fun getAllLaporan(): List<ArrayList<Double>>? = remoteDataSource.getAllLaporan()

    override suspend fun getAllLaporanList() : List<Laporan>? = remoteDataSource.getAllLaporanList()

    override suspend fun modifyLaporanStatus(laporan_id: String, status: String): Int = remoteDataSource.modifyLaporanStatus(laporan_id, status)

}

