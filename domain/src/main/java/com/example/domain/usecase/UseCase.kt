package com.example.domain.usecase

import com.example.domain.model.User

interface UseCase {
    suspend fun getUsers() : List<User>?

}