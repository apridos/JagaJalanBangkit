package com.example.jagajalanbangkit.admin.detail.screen

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.domain.model.Laporan
import com.example.jagajalanbangkit.databinding.ActivityDetailBinding
import com.example.jagajalanbangkit.viewmodels.LaporanViewModel
import com.example.jagajalanbangkit.viewmodels.UserViewModel
import com.example.jagajalanbangkit.viewmodels.ViewModelFactory
import kotlinx.coroutines.*
import javax.inject.Inject

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    @Inject
    lateinit var factory : ViewModelFactory

    private val laporanViewModel : LaporanViewModel by viewModels {
        factory
    }

    private lateinit var laporan : Laporan

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        laporan = intent.getParcelableExtra<Laporan>("laporan") as Laporan
        setContentView(binding.root)

        setupView()
        binding.apply {
            btnBack.setOnClickListener {
                this@DetailActivity.finish()
            }

            btnPending.setOnClickListener {
                Toast.makeText(this@DetailActivity, "Perbaikan ditunda", Toast.LENGTH_SHORT).show()
                pendingLaporan()
            }
            btnLapor.setOnClickListener {
                Toast.makeText(this@DetailActivity, "Kerusakan sudah diperbaiaki", Toast.LENGTH_SHORT).show()

                acceptLaporan()
            }
                }
            }

    private fun acceptLaporan(){
        Log.d("called", "called")
        val code = lifecycleScope.async(Dispatchers.Default) {
            launch{
                laporanViewModel.modifyLaporanStatus(laporan.idLaporan!!, "DONE")
            }
        }

        lifecycleScope.async {
            code.await()
            Log.d("code", code.toString())

        }

    }

    private fun pendingLaporan(){
        runBlocking {
            Log.d("called", "called")
            val code = lifecycleScope.async(Dispatchers.Default) {
                launch{
                    laporanViewModel.modifyLaporanStatus(laporan.idLaporan!!, "PENDING")
                }
            }

            lifecycleScope.async {
                code.await()
                Log.d("code", code.toString())
            }
        }
    }

    fun setupView(){
        if(laporan.status == "DONE"){
            binding.btnLapor.isEnabled = false
            binding.btnLapor.isClickable = false
        }
        binding.tvAlamat.text = laporan.alamat
        binding.tvDeskripsi.text = laporan.deskripsi
        binding.tvKeterangan.text = laporan.kondisi_kerusakan
            Glide.with(this@DetailActivity)
                .load(laporan.foto)
                //.apply(RequestOptions().override(60, 30))
                .timeout(5000)
                .listener(object : RequestListener<Drawable> {
                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        binding.ivPhoto.setBackgroundColor(0XFEFEFEF)
                        return false
                    }

                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }
                })
                .into(binding.ivPhoto)
        binding.progressBar.visibility =  View.INVISIBLE
    }

}