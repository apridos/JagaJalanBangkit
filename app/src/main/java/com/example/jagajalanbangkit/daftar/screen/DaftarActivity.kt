package com.example.jagajalanbangkit.daftar.screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.jagajalanbangkit.databinding.ActivityDaftarBinding
import com.example.jagajalanbangkit.login.screen.LoginActivity

class DaftarActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDaftarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDaftarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            progressBar.visibility = View.INVISIBLE

            btnBack.setOnClickListener {
                this@DaftarActivity.finish()
            }

            btnMasuk.setOnClickListener {
                val intent = Intent(this@DaftarActivity, LoginActivity::class.java)
                startActivity(intent)
            }
        }
    }
}