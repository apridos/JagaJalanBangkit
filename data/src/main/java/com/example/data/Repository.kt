package com.example.data

import com.example.data.source.RemoteDataSource
import com.example.data.source.network.response.ReauthResponse
import com.example.data.utils.DataMapper
import com.example.domain.model.Laporan
import com.example.domain.model.Login
import com.example.domain.model.Reauth
import com.example.domain.model.User
import com.example.domain.repository.IRepository
import kotlinx.coroutines.coroutineScope
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

}

