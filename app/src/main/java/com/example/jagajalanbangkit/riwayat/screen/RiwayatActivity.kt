package com.example.jagajalanbangkit.riwayat.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jagajalanbangkit.databinding.ActivityRiwayatBinding

class RiwayatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRiwayatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRiwayatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnBack.setOnClickListener {
                this@RiwayatActivity.finish()
            }
        }
    }
}