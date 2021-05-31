package com.example.domain.usecase

import com.example.domain.model.User
import com.example.domain.repository.IRepository
import javax.inject.Inject

class Interactor @Inject constructor(val repository : IRepository) : UseCase{
    override suspend fun getUsers() : List<User>? = repository.getUsers()
}