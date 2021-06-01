package com.example.domain.usecase

import com.example.domain.model.Login
import com.example.domain.model.User

interface UseCase {

    suspend fun login(user : User) : Login?

    suspend fun getUsers(token : String) : String

}