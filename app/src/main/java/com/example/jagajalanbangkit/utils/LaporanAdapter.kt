package com.example.jagajalanbangkit.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.domain.model.Laporan
import com.example.jagajalanbangkit.databinding.RiwayatItemListBinding
import java.util.ArrayList

class LaporanAdapter : RecyclerView.Adapter<LaporanAdapter.LaporanViewHolder>() {

    private val listLaporan = ArrayList<Laporan>()

    interface OnItemClickCallback {
        fun onItemClicked(data: Laporan)
    }

    interface OnItemLongClickedCallback {
        fun onItemClicked(data: Laporan)
    }

    fun setData(pairList : List<Laporan>){
        listLaporan.clear()
        listLaporan.addAll(pairList)
        notifyDataSetChanged()
    }


    inner class LaporanViewHolder(val binding : RiwayatItemListBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaporanViewHolder =
        LaporanViewHolder(RiwayatItemListBinding.inflate(LayoutInflater.from(parent.context),  parent, false))

    override fun onBindViewHolder(holder: LaporanAdapter.LaporanViewHolder, position: Int) {
        val laporan = listLaporan[position]
        holder.binding.tvAlamat.text = laporan.alamat
        Glide.with(holder.itemView.context)
            .load(laporan.foto)
            .apply(RequestOptions().override(60, 30))
            .timeout(10000)
            .into(holder.binding.ivFotoJalan)
    }

    override fun getItemCount(): Int = listLaporan.size
}