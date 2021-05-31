package com.example.jagajalanbangkit.viewmodels

import androidx.lifecycle.ViewModel
import com.example.domain.usecase.Interactor
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class UserViewModel (val interactor : Interactor) : ViewModel() {


    val users = GlobalScope.async {
        interactor.getUsers()
    }
}