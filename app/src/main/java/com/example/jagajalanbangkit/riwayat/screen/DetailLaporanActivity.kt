package com.example.jagajalanbangkit.riwayat.screen

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.domain.model.Laporan
import com.example.jagajalanbangkit.databinding.ActivityDetailBinding
import com.example.jagajalanbangkit.databinding.ActivityDetailLaporanBinding

class DetailLaporanActivity : AppCompatActivity() {
    private lateinit var binding :  ActivityDetailLaporanBinding
    private lateinit var laporan : Laporan

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailLaporanBinding.inflate(layoutInflater)
        laporan = intent.getParcelableExtra<Laporan>("laporan") as Laporan
        setContentView(binding.root)

        setupView()
        binding.apply {
            btnBack.setOnClickListener {
                this@DetailLaporanActivity.finish()
            }

        }
    }

    fun setupView(){

        binding.tvAlamat.text = laporan.alamat
        binding.tvDeskripsi.text = laporan.deskripsi
        binding.tvKeterangan.text = laporan.kondisi_kerusakan
        Glide.with(this@DetailLaporanActivity)
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