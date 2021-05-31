package com.example.domain.usecase

import com.example.data.Repository
import com.example.domain.model.User

class Interactor ( val repository : Repository) : UseCase{
    override suspend fun getUsers() : List<User>? = repository.getUsers()
}