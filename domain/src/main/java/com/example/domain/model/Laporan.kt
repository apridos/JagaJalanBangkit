package com.example.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Laporan(
    var alamat : String,
    var foto : String,
    var kondisi_kerusakan : String,
    var deskripsi : String,
    var latitude : Double,
    var longitude : Double

) : Parcelable