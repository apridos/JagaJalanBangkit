package com.example.jagajalanbangkit.admin.laporan.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jagajalanbangkit.databinding.ActivityLaporanBinding

class LaporanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLaporanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLaporanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnBack.setOnClickListener {
                this@LaporanActivity.finish()
            }
        }
    }
}