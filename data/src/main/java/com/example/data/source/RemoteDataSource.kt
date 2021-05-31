package com.example.data.source

import com.example.data.source.network.api.ApiService
import com.example.data.source.network.response.UsersRemote
import com.example.data.utils.DataMapper
import java.lang.Exception


class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getUsers(): List<UsersRemote>?{
        return try{
            val usersRemote = arrayListOf<UsersRemote>()
            val responses = apiService.getUsers()
            responses.map{
                DataMapper.mapUsersRemoteToUserRemotes(it)
            }

            usersRemote
        }catch (e : Exception){
            null
        }
    }
}