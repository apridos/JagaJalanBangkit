package com.example.jagajalanbangkit.admin.detail.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jagajalanbangkit.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnBack.setOnClickListener {
                this@DetailActivity.finish()
            }
        }
    }
}