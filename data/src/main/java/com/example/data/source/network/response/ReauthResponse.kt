package com.example.data.source.network.response

import com.google.gson.annotations.SerializedName

data class ReauthResponse (
    @field:SerializedName("access_token")
    var token : String,

    @field:SerializedName("refresh_token")
    var refreshToken : String
)