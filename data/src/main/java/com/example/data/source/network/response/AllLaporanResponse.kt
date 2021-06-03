package com.example.data.source.network.response

import com.google.gson.annotations.SerializedName

data class AllLaporanResponse(
    val listLaporan : ArrayList<LinkedHashMap<String, Any>>
)