package com.example.domain.usecase

import com.example.domain.model.Laporan
import com.example.domain.model.Login
import com.example.domain.model.Reauth
import com.example.domain.model.User
import com.example.domain.repository.IRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Interactor @Inject constructor(val repository : IRepository) : UseCase{

    override suspend fun login(user : User) : Login? = repository.login(user)

    override suspend fun getUsers(token : String) : String = repository.getUsers(token)

    override suspend fun createLaporan(token : String, laporan : Laporan) : Int = repository.createLaporan(token, laporan)

    override suspend fun reAuth(refreshToken: String) : Reauth? = repository.reAuth(refreshToken)

    override suspend fun getUserLaporans(token : String) : List<Laporan>? = repository.getUserLaporans(token)

    override suspend fun createUser(user: User) : Int = repository.createUser(user)
}