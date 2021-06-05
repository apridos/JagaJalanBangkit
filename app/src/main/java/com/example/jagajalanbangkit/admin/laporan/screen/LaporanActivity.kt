package com.example.jagajalanbangkit.admin.laporan.screen

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.Laporan
import com.example.jagajalanbangkit.MyApplication
import com.example.jagajalanbangkit.R
import com.example.jagajalanbangkit.adapter.LaporanAdapter
import com.example.jagajalanbangkit.admin.detail.screen.DetailActivity
import com.example.jagajalanbangkit.databinding.ActivityLaporanBinding
import com.example.jagajalanbangkit.home.screen.HomeActivity
import com.example.jagajalanbangkit.login.screen.LoginActivity
import com.example.jagajalanbangkit.viewmodels.LaporanViewModel
import com.example.jagajalanbangkit.viewmodels.ViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

class LaporanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLaporanBinding

    @Inject
    lateinit var factory: ViewModelFactory

    private val laporanViewModel: LaporanViewModel by viewModels{
        factory
    }

    private var listLaporan : List<Laporan>? = null

    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var rvRiwayat : RecyclerView

    private lateinit var adapter : LaporanAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLaporanBinding.inflate(layoutInflater)
        setContentView(binding.root)
        MyApplication.appComponent.inject(this)

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)

        binding.progressBar.visibility = View.VISIBLE
        rvRiwayat = binding.rvLaporan
        adapter = LaporanAdapter()
        rvRiwayat.adapter = adapter
        rvRiwayat.layoutManager = LinearLayoutManager(this@LaporanActivity)
        GlobalScope.launch(Dispatchers.Main) {
            setupList(checkToken())
        }
        adapter.setOnItemClickCallback(object : LaporanAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Laporan) {
                showSelectedLaporan(data)
            }
        })
        binding.apply {
            btnBack.setOnClickListener {
                this@LaporanActivity.finish()
            }
        }
        setContentView(binding.root)
    }

    private fun showSelectedLaporan(laporan: Laporan) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("laporan", laporan)
        startActivity((intent))
    }

    private fun checkToken() : String{
        val token = sharedPreferences.getString(getString(R.string.key_token), null)
        if(token == null){
            startActivity(Intent(this@LaporanActivity, LoginActivity::class.java))
            finish()
        }
        return token!!
    }

    suspend fun setupList(token : String): Unit {
        val setup = GlobalScope.async {
            listLaporan = laporanViewModel.getAllLaporanList()

        }
        setup.await()
        val images = GlobalScope.async(Dispatchers.Main) {
            if(listLaporan != null){
                adapter.setData(listLaporan!!)
            }
        }

        images.await()
        binding.progressBar.visibility = View.INVISIBLE
    }
}