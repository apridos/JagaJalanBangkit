package com.example.data.utils

import com.example.data.source.network.response.LaporanResponse
import com.example.data.source.network.response.LoginResponse
import com.example.data.source.network.response.ReauthResponse
import com.example.domain.model.Laporan
import com.example.domain.model.Login
import com.example.domain.model.Reauth
import com.google.gson.Gson
import org.json.JSONObject

object DataMapper {

    fun mapLaporanResponseToLaporan(laporanResponse: LaporanResponse) : Laporan{
        return Laporan(
            alamat = laporanResponse.alamat,
            kondisi_kerusakan = laporanResponse.kondisi_kerusakan,
            deskripsi = laporanResponse.kondisi_kerusakan,
            idLaporan = laporanResponse.idLaporan,
            latitude = laporanResponse.latitude,
            longitude = laporanResponse.longitude,
            foto = laporanResponse.foto
        )

    }


    fun mapReauthResponseToReauth(reauthResponse: ReauthResponse) : Reauth {
        return Reauth(
                token = "Bearer " + reauthResponse.token,
                refreshToken = reauthResponse.refreshToken
            )
    }

    fun mapLoginResponseToLogin(loginResponse: LoginResponse) : Login {
        var login : Login
        loginResponse.apply {
           login = Login(
                    email = this.email,
                    displayName = this.displayName,
                    password = null,
                    token = "Bearer " + this.token.toString(),
                    refreshToken = this.refreshToken,
                    userId = this.userId
                    )
        }
        return login
    }
}