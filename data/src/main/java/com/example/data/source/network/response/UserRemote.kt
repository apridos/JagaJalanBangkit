package com.example.data.source.network.response

import com.squareup.moshi.Json

data class UserRemote(
    @Json(name = "email")
    var email : String,

    @Json(name = "password")
    var password : String,

    @Json(name = "displayName")
    var displayName : String,

    @Json(name = "role")
    var role: String
)