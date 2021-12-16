package com.example.fdbsilverfilm.viewmodel

import androidx.lifecycle.ViewModel
import com.example.fdbsilverfilm.model.Picture

class PictureDetailsViewModel(var picture: Picture) : ViewModel() {

    var data: List<String>

    init {
        val arrayList = ArrayList<String>()
        arrayList.add(picture.title)
        arrayList.add(if (picture.meta.focal != 0.0f) "f1/${picture.meta.focal}" else "")
        arrayList.add(if (picture.meta.opening != 0.0f) "1/${picture.meta.opening}" else "")
        arrayList.add(if (picture.meta.time != 0.0) "${picture.meta.time}s" else "")
        arrayList.add(picture.meta.mode)
        arrayList.add(picture.meta.lens)
        arrayList.add(picture.date)
        arrayList.add("${picture.meta.latitude} , ${picture.meta.longitude}")
        data = arrayList.toList()
    }

}