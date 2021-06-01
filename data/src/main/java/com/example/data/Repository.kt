package com.example.data

import com.example.data.source.RemoteDataSource
import com.example.data.utils.DataMapper
import com.example.domain.model.Laporan
import com.example.domain.model.Login
import com.example.domain.model.User
import com.example.domain.repository.IRepository
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class Repository @Inject constructor(private val remoteDataSource: RemoteDataSource) : IRepository{


    override suspend fun login(user: User): Login? {
        return remoteDataSource.login(user.email, user.password)?.let {
            DataMapper.mapLoginResponseToLogin(it)
        }
    }

    override suspend fun getUsers(token : String): String {
        return remoteDataSource.getUsers(token)
    }

    override suspend fun createLaporan(token : String, laporan: Laporan): Boolean {
        return remoteDataSource.createLaporan(token, laporan)
    }

}

