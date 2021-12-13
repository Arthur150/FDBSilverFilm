package com.example.fdbsilverfilm.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fdbsilverfilm.R
import com.example.fdbsilverfilm.manager.SharedPreferencesManager
import com.example.fdbsilverfilm.model.Film
import com.example.fdbsilverfilm.view.MainActivity
import com.example.fdbsilverfilm.viewmodel.FilmListViewModel

class FilmAdapter(
    private val context: Context,
    private var films: List<Film>,
    private var filmViewModel: FilmListViewModel
) : RecyclerView.Adapter<FilmAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name = view.findViewById<TextView>(R.id.filmItemName)
        val iso = view.findViewById<TextView>(R.id.filmItemIso)
        val countPictures = view.findViewById<TextView>(R.id.filmItemCountPictures)
        val deleteButton = view.findViewById<ImageButton>(R.id.filmItemDelete)
        val cameraName = view.findViewById<TextView>(R.id.filmItemCameraName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.film_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = films[position].name
        holder.iso.text = films[position].iso.toString()
        holder.countPictures.text = "${films[position].pictures.count()}/${films[position].nbPoses}"
        holder.cameraName.text = films[position].cameraName

        holder.itemView.setOnClickListener {
            films[position].id?.let {
                SharedPreferencesManager.saveCurrentFilm(context, it)
            }

            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }

        holder.deleteButton.setOnClickListener {
            films[position].let {

                val builder = AlertDialog.Builder(context)
                builder.setTitle(context.getString(R.string.delete_film))
                builder.setMessage(context.getString(R.string.ask_delete_film))

                builder.setPositiveButton(android.R.string.yes) { _, _ ->
                    filmViewModel.deleteFilm(it)
                }

                builder.setNegativeButton(android.R.string.no) { dialog, _ ->
                    dialog.dismiss()
                }

                builder.show()
            }
        }
    }

    override fun getItemCount() = films.size

    fun updateValue(list: List<Film>) {
        films = list
        notifyDataSetChanged()
    }
}