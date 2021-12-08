package com.example.fdbsilverfilm.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.fdbsilverfilm.R
import com.example.fdbsilverfilm.model.Film

class FilmAdapter(
    private val context: Context,
    private var films: List<Film>,
) : RecyclerView.Adapter<FilmAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name = view.findViewById<TextView>(R.id.filmItemName)
        val iso = view.findViewById<TextView>(R.id.filmItemIso)
        val countPictures = view.findViewById<TextView>(R.id.filmItemCountPictures)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.film_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = films[position].name
        holder.iso.text = films[position].iso.toString()
        holder.countPictures.text = "${films[position].pictures.count()}/${films[position].nbPoses}"

        holder.itemView.setOnClickListener {
            //TODO quand tu clique ça fait un truc

            Toast.makeText(
                context,
                "${context.getString(R.string.not_implemented_yet)}  ${context.getString(R.string.slut)}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun getItemCount() = films.size

    fun updateValue(list: List<Film>){
        films = list
        notifyDataSetChanged()
    }
}