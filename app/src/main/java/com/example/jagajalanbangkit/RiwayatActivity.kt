package com.example.jagajalanbangkit

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jagajalanbangkit.databinding.ActivityRiwayatBinding
import com.example.jagajalanbangkit.login.screen.LoginActivity
import com.example.jagajalanbangkit.utils.LaporanAdapter
import com.example.jagajalanbangkit.viewmodels.LaporanViewModel
import com.example.jagajalanbangkit.viewmodels.ViewModelFactory
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import javax.inject.Inject

class RiwayatActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRiwayatBinding

    @Inject
    lateinit var factory : ViewModelFactory

    private val laporanViewModel : LaporanViewModel by viewModels {
        factory
    }

    private lateinit var rvRiwayat : RecyclerView

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRiwayatBinding.inflate(layoutInflater)
        MyApplication.appComponent.inject(this)
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        var token = checkToken()

        rvRiwayat = binding.rvRiwayat
        val adapter = LaporanAdapter()
        rvRiwayat.adapter = adapter
        rvRiwayat.layoutManager = LinearLayoutManager(this@RiwayatActivity)


        GlobalScope.async {
            val listLaporan = laporanViewModel.getUserLaporans(token)
            Log.d("laporanny", listLaporan.toString())
            if (listLaporan != null) {
                adapter.setData(listLaporan)
            }
        }

        setContentView(binding.root)
    }

    private fun checkToken() : String{
        val token = sharedPreferences.getString(getString(R.string.key_token), null)
        if(token == null){
            startActivity(Intent(this@RiwayatActivity, LoginActivity::class.java))
        }
        return token!!
    }

}