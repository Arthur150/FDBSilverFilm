package com.example.fdbsilverfilm.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.fdbsilverfilm.R
import com.example.fdbsilverfilm.database.SilverFilmDataBase
import com.example.fdbsilverfilm.model.Film
import com.example.fdbsilverfilm.model.Meta
import com.example.fdbsilverfilm.model.Picture
import com.example.fdbsilverfilm.repository.SilverFilmRepository
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity(){
    @SuppressLint("WrongThread")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val arrayList = ArrayList<Picture>()
        arrayList.add(Picture(1,1,"tait","dsd", Meta(25F, 45F, 45.0,"dzsf")))
        arrayList.add(Picture(2,1,"gnn","dsdsdfs", Meta(255F, 45F, 45.0,"dzsdf")))
        arrayList.add(Picture(3,1,"fdsfsd","sdfsdfsdf", Meta(25F, 45F, 45.0,"dzfsf")))

        val arrayList2 = ArrayList<Film>()
        arrayList2.add(Film(null,"test","ba",25,"ded",35, arrayList))
        arrayList2.add(Film(null,"ba","ba",25,"ded",35, arrayList))
        arrayList2.add(Film(null,"ba","ba",25,"ded",35, arrayList))



        val silverDB = SilverFilmDataBase.getDatabase(this).silverFilmDAO()
        val repository = SilverFilmRepository(silverDB)



        GlobalScope.launch {
            //SilverFilmDataBase.getDatabase(applicationContext).clearAllTables()

          // runBlocking {  repository.insertAllFilm(arrayList2)}
             //runBlocking {  repository.insertFilm(Film(null,"coucou","ba",25,"ded",35, arrayList))}

            val test = repository.getAllFilm()
            Log.d("test", "test")
            Log.d("test", test.toString())
        }



    }

}