package com.example.jagajalanbangkit.splashscreen.screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.jagajalanbangkit.databinding.ActivitySplashscreenBinding
import com.example.jagajalanbangkit.home.screen.HomeActivity

class SplashscreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashscreenBinding
    private lateinit var handler: Handler
    private val delay: Long = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handler = Handler(mainLooper)
        handler.postDelayed({
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }, delay)
    }
}