package com.example.jagajalanbangkit.daftar.screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.domain.model.User
import com.example.jagajalanbangkit.MyApplication
import com.example.jagajalanbangkit.databinding.ActivityDaftarBinding
import com.example.jagajalanbangkit.login.screen.LoginActivity
import com.example.jagajalanbangkit.viewmodels.UserViewModel
import com.example.jagajalanbangkit.viewmodels.ViewModelFactory
import kotlinx.coroutines.*
import java.lang.Runnable
import javax.inject.Inject

class DaftarActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDaftarBinding

    @Inject
    lateinit var factory : ViewModelFactory

    private val userViewModel : UserViewModel by viewModels {
        factory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDaftarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            progressBar.visibility = View.INVISIBLE
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

            etName.addTextChangedListener(object : TextWatcher {
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
                        etName.setError("Nama minimal 8 karakter")
                    }
                }
            })

            btnRegister.setOnClickListener {
                if(etName.text.toString().length < 8 || etName.error != null){
                    Toast.makeText(this@DaftarActivity, "Nama minimal 8 karakter", Toast.LENGTH_SHORT).show()
                }else if(etEmail.error != null || etEmail.text.toString().isEmpty()){
                    Toast.makeText(this@DaftarActivity, "Alamat email tidak valid", Toast.LENGTH_SHORT).show()
                }else if(etPassword.text.toString().length < 8){
                    Toast.makeText(this@DaftarActivity, "Lengkapi password", Toast.LENGTH_SHORT).show()
                }else{
                    progressBar.visibility = View.VISIBLE
                    GlobalScope.launch(Dispatchers.Default) {
                        val result = userViewModel.createUser(User(
                            email = etEmail.text.toString(),
                            password = etPassword.text.toString(),
                            displayName = etName.text.toString(),
                            role = "user"
                        ))

                        if(result == 201){
                            runOnUiThread { Runnable {
                                toastBerhasil()
                            } }
                            delay(2000)
                            startActivity(Intent(this@DaftarActivity, LoginActivity::class.java))
                        }else{
                            runOnUiThread { Runnable {
                                toastGagal()
                            } }
                        }
                        progressBar.visibility = View.INVISIBLE
                    }

                }
            }
        }
        MyApplication.appComponent.inject(this)
    }

    fun validateEmail(email : String): Boolean{
        return (!email.isEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
    }

    private fun toastGagal(){
        Toast.makeText(this@DaftarActivity, "Email sudah digunakan", Toast.LENGTH_SHORT).show()
        binding.progressBar.visibility = View.INVISIBLE
        binding.btnRegister.isClickable = true
        binding.btnRegister.isEnabled = true
    }

    private fun toastBerhasil(){
        Toast.makeText(this@DaftarActivity, "Akun berhasil dibuat", Toast.LENGTH_SHORT).show()
        binding.progressBar.visibility = View.INVISIBLE
        binding.btnRegister.isClickable = true
        binding.btnRegister.isEnabled = true
    }
}