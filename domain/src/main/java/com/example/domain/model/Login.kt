package com.example.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Login(
    var email : String?,

    var password : String?,

    var displayName : String?,

    var token : String?,

    var refreshToken : String?
) : Parcelable