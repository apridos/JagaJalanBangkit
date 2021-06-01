package com.example.jagajalanbangkit.home.screen

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.jagajalanbangkit.MyApplication
import com.example.jagajalanbangkit.R
import com.example.jagajalanbangkit.databinding.ActivityHomeBinding
import com.example.jagajalanbangkit.viewmodels.UserViewModel
import com.example.jagajalanbangkit.viewmodels.ViewModelFactory
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    @Inject
    lateinit var factory: ViewModelFactory

    private val userViewModel: UserViewModel by viewModels{
        factory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        MyApplication.appComponent.inject(this)

        GlobalScope.async {
            val login = userViewModel.login()
            login?.let {
                val sharedPref = getPreferences(Context.MODE_PRIVATE)
                with(sharedPref.edit()) {
                    putString(getString(R.string.key_user_id), it.id)
                    putString(getString(R.string.key_email), it.email)
                    putString(getString(R.string.key_name), it.displayName)
                    putString(getString(R.string.key_token), it.token)
                    putString(getString(R.string.key_refresh_token), it.refreshToken)
                    apply()
                }
            }
            val token = getPreferences(Context.MODE_PRIVATE).getString(getString(R.string.key_token), null).toString()

            val users = userViewModel.getUsers(token)

            Log.d("ini", users)

        }



        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}