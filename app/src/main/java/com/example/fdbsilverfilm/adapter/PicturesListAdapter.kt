package com.example.fdbsilverfilm.adapter

import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.fdbsilverfilm.R
import com.example.fdbsilverfilm.model.Globals
import com.example.fdbsilverfilm.model.Picture
import com.example.fdbsilverfilm.view.PictureDetailsActivity

class PicturesListAdapter(private val list: List<Picture>?, private val context: Context) :
    RecyclerView.Adapter<PicturesListAdapter.PicturesListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PicturesListViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.picture_item, parent, false)
        return PicturesListViewHolder(root)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: PicturesListViewHolder, position: Int) {
        holder.bind(list?.get(position))
        holder.itemView.setOnClickListener {
            val picture = list?.get(position)
            val intent = Intent(context, PictureDetailsActivity::class.java)
            intent.putExtra(Globals.PICTURE_EXTRA_TAG, picture)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    inner class PicturesListViewHolder(binding: View) :
        RecyclerView.ViewHolder(binding) {
        private val title: TextView = binding.findViewById(R.id.title_picture_item)
        private val date: TextView = binding.findViewById(R.id.date_picture_item)
        private val preview: ImageView = binding.findViewById(R.id.preview_image_picture_item)
        private val positionPicture : TextView = binding.findViewById(R.id.position_picture_item)

        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(picture: Picture?) {
            if (picture != null) {
                date.text = picture.date
                positionPicture.text = picture.id.toString()

                if (picture.preview.isNotEmpty()) {
                    preview.setImageBitmap(Globals.stringToBitmap(picture.preview))
                }

                if (picture.title.isNotEmpty()){
                    title.text = picture.title
                }
            }
        }
    }
}
