package com.example.domain.repository

import com.example.domain.model.User

interface IRepository {
    suspend fun getUsers() : List<User>?
}