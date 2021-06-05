package com.example.jagajalanbangkit.admin.home.screen

import android.content.Intent
import android.content.SharedPreferences
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import com.example.jagajalanbangkit.MyApplication
import com.example.jagajalanbangkit.R
import com.example.jagajalanbangkit.admin.laporan.screen.LaporanActivity
import com.example.jagajalanbangkit.databinding.ActivityAdminHomeBinding
import com.example.jagajalanbangkit.viewmodels.LaporanViewModel
import com.example.jagajalanbangkit.viewmodels.ViewModelFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

class AdminHomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAdminHomeBinding

    @Inject
    lateinit var factory: ViewModelFactory

    private val laporanViewModel: LaporanViewModel by viewModels{
        factory
    }

    private lateinit var sharedPreferences: SharedPreferences

    private var listLaporan : List<ArrayList<Double>>? = null

    private lateinit var mapFragment : SupportMapFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MyApplication.appComponent.inject(this)
        binding = ActivityAdminHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        binding.progressBar.visibility = View.VISIBLE
        GlobalScope.launch(Dispatchers.Main) {
            getAllLaporan()
        }
        binding.btnLaporan.setOnClickListener {
            startActivity(Intent(this@AdminHomeActivity, LaporanActivity::class.java))
        }

        setContentView(binding.root)

    }

    private suspend fun getAllLaporan(){
        val wait = GlobalScope.async {
            listLaporan = laporanViewModel.getAllLaporan()

            mapFragment = supportFragmentManager.findFragmentById(R.id.map_container) as SupportMapFragment
            listLaporan
        }
        println(wait.await())
        mapFragment.getMapAsync(OnMapReadyCallback {
            if (listLaporan != null) {
                for (koordinat in listLaporan!!) {
                    if(koordinat.size > 1){
                        val location = LatLng(koordinat[1], koordinat[0])
                        it.addMarker(MarkerOptions().position(location))
                    }
                }
            }
        })
        binding.progressBar.visibility = View.INVISIBLE

    }
}