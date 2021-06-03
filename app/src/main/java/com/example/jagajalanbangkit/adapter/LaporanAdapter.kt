package com.example.jagajalanbangkit.adapter

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.domain.model.Laporan
import com.example.jagajalanbangkit.databinding.RiwayatItemListBinding


class LaporanAdapter() : RecyclerView.Adapter<LaporanAdapter.ListViewHolder>() {


    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(laporan: Laporan)
    }
    private val listLaporan = ArrayList<Laporan>()


    fun setData(list : List<Laporan>){
        listLaporan.clear()
        listLaporan.addAll(list)
        notifyDataSetChanged()
    }

    class ListViewHolder(val binding : RiwayatItemListBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder =
        ListViewHolder(RiwayatItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val laporan = listLaporan[position]

        Glide.with(holder.itemView.context)
            .load(laporan.foto)
            .timeout(10000)
            .listener(object : RequestListener<Drawable>{
                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    holder.binding.ivFotoJalan.setBackgroundColor(0XFEFEFEF)
                    return false
                }

                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    Log.d("GAGAL MEMUAT GAMBAR", e.toString())
                    return false
                }
            })
            .into(holder.binding.ivFotoJalan)

        holder.itemView.setOnClickListener{
            onItemClickCallback.onItemClicked(laporan)
        }
    }

    override fun getItemCount() : Int = listLaporan.size

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

}