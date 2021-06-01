package com.example.jagajalanbangkit.viewmodels

import androidx.lifecycle.ViewModel
import com.example.domain.model.Laporan
import com.example.domain.usecase.Interactor
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LaporanViewModel @Inject constructor(val interactor : Interactor) : ViewModel() {


}