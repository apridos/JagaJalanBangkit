package com.example.jagajalanbangkit.admin.home.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jagajalanbangkit.databinding.ActivityAdminHomeBinding

class AdminHomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAdminHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}