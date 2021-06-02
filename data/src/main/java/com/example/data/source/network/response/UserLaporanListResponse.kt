package com.example.data.source.network.response

import com.google.gson.annotations.SerializedName

data class UserLaporanListResponse (
    @field:SerializedName("laporan")
    var laporanList : ArrayList<LinkedHashMap<String, Any>>
)