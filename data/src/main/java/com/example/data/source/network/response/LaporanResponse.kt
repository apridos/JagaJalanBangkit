package com.example.data.source.network.response

import com.google.gson.annotations.SerializedName

data class LaporanResponse (
    @field:SerializedName("id")
    var idLaporan : String,

    @field:SerializedName("alamat")
    var alamat : String,

    @field:SerializedName("kondisi_kerusakan")
    var kondisi_kerusakan : String,

    @field:SerializedName("deskripsi")
    var deskripsi : String,

    @field:SerializedName("longitude")
    var longitude : Double,

    @field:SerializedName("latitude")
    var latitude : Double,

    @field:SerializedName("foto")
    var foto : String
)