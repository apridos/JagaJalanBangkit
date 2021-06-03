package com.example.jagajalanbangkit.login.screen

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.auth0.android.jwt.JWT
import com.example.domain.model.User
import com.example.jagajalanbangkit.MyApplication
import com.example.jagajalanbangkit.R
import com.example.jagajalanbangkit.admin.home.screen.AdminHomeActivity
import com.example.jagajalanbangkit.daftar.screen.DaftarActivity
import com.example.jagajalanbangkit.databinding.ActivityLaporBinding
import com.example.jagajalanbangkit.databinding.ActivityLoginBinding
import com.example.jagajalanbangkit.home.screen.HomeActivity
import com.example.jagajalanbangkit.viewmodels.UserViewModel
import com.example.jagajalanbangkit.viewmodels.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject
import kotlin.math.log

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding

    @Inject
    lateinit var factory: ViewModelFactory

    private val userViewModel : UserViewModel by viewModels {
        factory
    }

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)

        binding.progressBar.visibility = View.INVISIBLE

        MyApplication.appComponent.inject(this)
        binding.apply {
            etEmail.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }

                override fun afterTextChanged(s: Editable?) {
                    if(!validateEmail(s.toString())){
                        etEmail.setError("Alamat email tidak valid")
                    }
                }

            })
            etPassword.addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                }

                override fun afterTextChanged(s: Editable?) {
                    if(s.toString().length < 8){
                        etPassword.setError("Password minimal 8 karakter")
                    }
                }

            })
            btnRegister.setOnClickListener{
                startActivity(Intent(this@LoginActivity, DaftarActivity::class.java))
            }
            btnLogin.setOnClickListener {
                if(etEmail.error != null || etEmail.text.toString().isEmpty()){
                    Toast.makeText(this@LoginActivity, "Periksa email", Toast.LENGTH_SHORT).show()
                }else if(etPassword.text.toString().length < 8){
                    Toast.makeText(this@LoginActivity, "Lengkapi password", Toast.LENGTH_SHORT).show()
                }else{
                    progressBar.visibility = View.VISIBLE
                    btnLogin.isEnabled = false
                    btnLogin.isClickable = false
                    GlobalScope.launch {
                        val result = userViewModel.login(User(binding.etEmail.text.toString(), binding.etPassword.text.toString()))
                        var role : String? = null
                        if ( result != null){
                            with(sharedPreferences.edit()) {
                                putString(getString(R.string.key_email), result.email)
                                putString(getString(R.string.key_name), result.displayName)
                                putString(getString(R.string.key_token), result.token)
                                putString(getString(R.string.key_refresh_token), result.refreshToken)
                                putString(getString(R.string.key_user_id), result.userId)
                                commit()
                                binding.progressBar.visibility = View.INVISIBLE
                                role = JWT(result.token!!.replace("Bearer ", "")).getClaim("role").asString()
                                if(role.toString() == "user"){
                                    Log.d("masuk", "home")
                                    startActivity(
                                        Intent(
                                            this@LoginActivity,
                                            HomeActivity::class.java
                                        )
                                    )
                                }else{
                                    startActivity(
                                        Intent(
                                            this@LoginActivity,
                                            AdminHomeActivity::class.java
                                        )
                                    )
                                }

                                finish()
                            }


                        }else{
                            runOnUiThread(Runnable {
                                toastGagal()
                            })
                        }
                    }
                    progressBar.visibility = View.INVISIBLE
                    btnLogin.isEnabled = true
                    btnLogin.isClickable = true
                }
            }
        }

        setContentView(binding.root)

    }

    fun validateEmail(email : String): Boolean{
        return (!email.isEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
    }

    private fun toastGagal(){
        Toast.makeText(this@LoginActivity, "Username atau password salah", Toast.LENGTH_SHORT).show()
        binding.progressBar.visibility = View.INVISIBLE
        binding.btnLogin.isClickable = true
        binding.btnLogin.isEnabled = true
    }
}