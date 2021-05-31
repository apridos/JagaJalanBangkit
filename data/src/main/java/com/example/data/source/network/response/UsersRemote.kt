package com.example.data.source.network.response

import com.google.gson.internal.LinkedHashTreeMap
import com.squareup.moshi.Json

data class UsersRemote (
    @Json(name = "user")
    var users : LinkedHashTreeMap<String, Any>
)