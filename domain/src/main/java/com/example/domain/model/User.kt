package com.example.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    var email : String,
    var password : String,
    var displayName : String,
    var role : String
) : Parcelable
