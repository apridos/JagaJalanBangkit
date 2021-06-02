package com.example.jagajalanbangkit.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.domain.model.Laporan
import com.example.domain.usecase.Interactor
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LaporanViewModel @Inject constructor(private val interactor : Interactor) : ViewModel() {

    suspend fun getUserLaporans(token : String) : List<Laporan>? = interactor.getUserLaporans(token)

    suspend fun createLaporan(token: String, laporan: Laporan) : Int = interactor.createLaporan(token, laporan)

}