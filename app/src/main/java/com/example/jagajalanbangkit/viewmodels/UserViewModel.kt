package com.example.jagajalanbangkit.viewmodels

import androidx.lifecycle.ViewModel
import com.example.domain.model.User
import com.example.domain.usecase.Interactor
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import javax.inject.Inject

class UserViewModel @Inject constructor(val interactor : Interactor) : ViewModel() {

    suspend fun getUsers(): List<User>? = interactor.getUsers()

    /*
    val users = GlobalScope.async {
        interactor.getUsers()
    }*/
}