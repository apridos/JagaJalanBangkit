package com.example.data.source.network.response

import com.squareup.moshi.Json

data class LaporanRemote(
    @Json(name = "alamat")
    var alamat : String,

    @Json(name = "kondisi_kerusakan")
    var kondisi_kerusakan : String,

    @Json(name = "deskripsi")
    var deskripsi : String,

    @Json(name = "foto")
    var foto : String,

    @Json(name = "longitude")
    var longitude : String,

    @Json(name = "latitude")
    var latitude : String
)
