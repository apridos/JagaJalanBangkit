package com.example.data.source.network.response

import com.squareup.moshi.Json

data class LoginResponse (
    @field:Json(name = "email")
    var email : String?,

    @field:Json(name = "password")
    var password : String?,

    @field:Json(name = "displayName")
    var displayName : String?,

    @field:Json(name = "idToken")
    var token : String?,

    @field:Json(name= "refreshToken")
    var refreshToken : String?
)