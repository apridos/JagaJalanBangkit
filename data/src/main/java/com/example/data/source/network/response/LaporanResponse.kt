package com.example.data.source.network.response

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

data class LaporanResponse (
    //@field:Json(name ="id")
    @field:SerializedName("id")
    var idLaporan : String
    )