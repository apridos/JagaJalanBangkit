package com.example.domain.model

import com.google.gson.annotations.SerializedName

data class Reauth (
    var token : String,

    var refreshToken : String
)