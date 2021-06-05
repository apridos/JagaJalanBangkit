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

    suspend fun reAuth(refreshToken : String) : Reauth? = interactor.reAuth(refreshToken)

    suspend fun createUser(user: User) : Int = interactor.createUser(user)

    suspend fun testToken(token : String) : Int = interactor.testToken(token)

}
