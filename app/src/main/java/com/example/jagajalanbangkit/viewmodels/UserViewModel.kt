package com.example.jagajalanbangkit.viewmodels

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.example.domain.model.Login
import com.example.domain.model.User
import com.example.domain.usecase.Interactor
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

class UserViewModel @Inject constructor(val interactor : Interactor) : ViewModel() {

    suspend fun login() : Login? {
        val login = interactor.login(User("aprido@gmail.com", "testpassword"))

        return login
    }

    suspend fun getUsers(token : String) : String = interactor.getUsers(token)

}
