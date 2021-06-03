package com.example.jagajalanbangkit.riwayat.screen

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.Laporan
import com.example.jagajalanbangkit.MyApplication
import com.example.jagajalanbangkit.R
import com.example.jagajalanbangkit.databinding.ActivityRiwayatBinding
import com.example.jagajalanbangkit.login.screen.LoginActivity
import com.example.jagajalanbangkit.adapter.LaporanAdapter
import com.example.jagajalanbangkit.viewmodels.LaporanViewModel
import com.example.jagajalanbangkit.viewmodels.ViewModelFactory
import kotlinx.coroutines.Dispatchers
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

        binding.progressBar.visibility = View.VISIBLE
        rvRiwayat = binding.rvRiwayat
        adapter = LaporanAdapter()
        rvRiwayat.adapter = adapter
        rvRiwayat.layoutManager = LinearLayoutManager(this@RiwayatActivity)
        GlobalScope.launch(Dispatchers.Main) {
            setupList(checkToken())
        }
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

    suspend fun setupList(token : String): Unit {
        val setup = GlobalScope.async {
            listLaporan = laporanViewModel.getUserLaporans(token)

        }

        setup.await()
        if(listLaporan != null){
            adapter.setData(listLaporan!!)
        }
        binding.progressBar.visibility = View.INVISIBLE



    }
}