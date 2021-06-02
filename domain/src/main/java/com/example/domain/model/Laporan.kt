package com.example.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Laporan(
    var idLaporan : String? = null,
    var alamat : String? = null,
    var foto : String? = null,
    var kondisi_kerusakan : String? = null,
    var deskripsi : String? = null,
    var latitude : Double? = null,
    var longitude : Double? = null

) : Parcelable