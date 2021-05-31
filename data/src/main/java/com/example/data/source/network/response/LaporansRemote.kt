package com.example.data.source.network.response

import com.google.gson.internal.LinkedHashTreeMap

data class LaporansRemote(
    var laporanList : List<LinkedHashTreeMap<String, Any>>
)