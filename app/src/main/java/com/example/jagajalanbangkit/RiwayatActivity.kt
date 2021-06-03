package com.example.jagajalanbangkit

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.Laporan
import com.example.jagajalanbangkit.databinding.ActivityRiwayatBinding
import com.example.jagajalanbangkit.login.screen.LoginActivity
import com.example.jagajalanbangkit.adapter.LaporanAdapter
import com.example.jagajalanbangkit.viewmodels.LaporanViewModel
import com.example.jagajalanbangkit.viewmodels.ViewModelFactory
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

class RiwayatActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRiwayatBinding

    private var listLaporan : List<Laporan>? = null

    @Inject
    lateinit var factory : ViewModelFactory

    private val laporanViewModel : LaporanViewModel by viewModels {
        factory
    }

    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var rvRiwayat : RecyclerView

    private lateinit var adapter : LaporanAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRiwayatBinding.inflate(layoutInflater)
        MyApplication.appComponent.inject(this)
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)

        rvRiwayat = binding.rvRiwayat
        adapter = LaporanAdapter()
        rvRiwayat.adapter = adapter
        rvRiwayat.hasFixedSize(true)
        rvRiwayat.layoutManager = LinearLayoutManager(this@RiwayatActivity)

        setContentView(binding.root)
    }

    private fun checkToken() : String{
        val token = sharedPreferences.getString(getString(R.string.key_token), null)
        if(token == null){
            startActivity(Intent(this@RiwayatActivity, LoginActivity::class.java))
            finish()
        }
        return token!!
    }
}