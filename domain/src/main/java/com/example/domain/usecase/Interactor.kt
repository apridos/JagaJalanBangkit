package com.example.domain.usecase

import com.example.domain.model.Login
import com.example.domain.model.User
import com.example.domain.repository.IRepository
import javax.inject.Inject

class Interactor @Inject constructor(val repository : IRepository) : UseCase{

    override suspend fun login(user : User) : Login? = repository.login(user)

    override suspend fun getUsers(token : String) : String = repository.getUsers(token)

}