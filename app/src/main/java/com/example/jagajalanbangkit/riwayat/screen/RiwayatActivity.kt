package com.example.jagajalanbangkit.riwayat.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.jagajalanbangkit.databinding.ActivityRiwayatBinding
import com.example.jagajalanbangkit.utils.SwipeToDelete

class RiwayatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRiwayatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRiwayatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnBack.setOnClickListener {
                this@RiwayatActivity.finish()
            }
        }

        val item = object : SwipeToDelete(this, 0, ItemTouchHelper.LEFT) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                // adapter.del(viewHolder.adapterPosition)
            }
        }

        val itemTouchHelper = ItemTouchHelper(item)
        //itemTouchHelper.attachToRecyclerView(recycler)
    }
}