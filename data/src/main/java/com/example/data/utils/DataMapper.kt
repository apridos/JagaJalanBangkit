package com.example.data.utils

import com.example.data.source.network.response.LoginResponse
import com.example.domain.model.Login
import com.google.gson.Gson
import org.json.JSONObject

object DataMapper {

    fun mapLoginResponseToLogin(loginResponse: LoginResponse) : Login {
        var login : Login
        loginResponse.apply {
           login = Login(
                    email = this.email,
                    displayName = this.displayName,
                    password = null,
                    token = this.token.toString(),
                    refreshToken = this.refreshToken
                    )
        }
        return login
    }
}