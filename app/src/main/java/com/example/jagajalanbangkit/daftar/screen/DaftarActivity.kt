package com.example.jagajalanbangkit.daftar.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jagajalanbangkit.databinding.ActivityDaftarBinding

class DaftarActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDaftarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar)
    }
}