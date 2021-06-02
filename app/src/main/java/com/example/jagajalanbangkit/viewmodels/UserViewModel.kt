package com.example.jagajalanbangkit.viewmodels

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.example.domain.model.Laporan
import com.example.domain.model.Login
import com.example.domain.model.Reauth
import com.example.domain.model.User
import com.example.domain.usecase.Interactor
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

class UserViewModel @Inject constructor(val interactor : Interactor) : ViewModel() {

    suspend fun login(user : User) : Login? = interactor.login(user)


    suspend fun getUsers(token : String) : String = interactor.getUsers(token)

    suspend fun createLaporan(token : String, laporan : Laporan) : Int {
        return interactor.createLaporan(token, laporan)
    }

    suspend fun reAuth(refreshToken : String) : Reauth? = interactor.reAuth(refreshToken)

}
