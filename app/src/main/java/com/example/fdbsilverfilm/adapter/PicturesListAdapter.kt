package com.example.fdbsilverfilm.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fdbsilverfilm.R
import com.example.fdbsilverfilm.model.Picture
import com.example.fdbsilverfilm.view.PictureDetailsActivity

class PicturesListAdapter(private val list: List<Picture>?, private val context: Context) :
    RecyclerView.Adapter<PicturesListAdapter.PicturesListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PicturesListViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.picture_item, parent, false)
        return PicturesListViewHolder(root)
    }

    override fun onBindViewHolder(holder: PicturesListViewHolder, position: Int) {
        holder.bind(list?.get(position))
        holder.itemView.setOnClickListener {
            val picture = list?.get(position)
            val intent = Intent(context, PictureDetailsActivity::class.java)
            intent.putExtra("comic", picture)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    inner class PicturesListViewHolder(private val binding: View) :
        RecyclerView.ViewHolder(binding) {
        private val title: TextView = binding.findViewById(R.id.title_picture_item)
        private val date: TextView = binding.findViewById(R.id.date_picture_item)

        fun bind(picture: Picture?) {
            title.text = picture?.title
            date.text = picture?.date
        }
    }
}
