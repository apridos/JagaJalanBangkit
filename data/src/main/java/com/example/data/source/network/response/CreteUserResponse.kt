package com.example.data.source.network.response

import com.google.gson.annotations.SerializedName

data class CreteUserResponse(
    @field:SerializedName("uid")
    var uid : String?
)