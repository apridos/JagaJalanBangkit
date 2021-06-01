package com.example.data.source.network.response

data class LoginRequest(
    var email : String,

    var password : String,

    var returnSecureToken : Boolean = true
)