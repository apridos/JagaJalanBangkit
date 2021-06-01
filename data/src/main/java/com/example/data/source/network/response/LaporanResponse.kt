package com.example.data.source.network.response

import com.squareup.moshi.Json

data class LaporanResponse (
    @field:Json(name ="id")
    var idLaporan : String
    )