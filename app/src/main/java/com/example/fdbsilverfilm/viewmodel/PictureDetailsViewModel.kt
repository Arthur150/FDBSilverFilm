package com.example.fdbsilverfilm.viewmodel

import androidx.lifecycle.ViewModel
import com.example.fdbsilverfilm.model.Picture

class PictureDetailsViewModel(var picture: Picture) : ViewModel() {

    var data : List<String>

    init {
        val arrayList = ArrayList<String>()
        arrayList.add(picture.title)
        arrayList.add(if (picture.meta.focal != 0.0f) picture.meta.focal.toString() else "")
        arrayList.add(if (picture.meta.opening != 0.0f) picture.meta.opening.toString() else "")
        arrayList.add(if (picture.meta.time != 0.0) picture.meta.time.toString() else "")
        arrayList.add(picture.meta.mode)
        arrayList.add(picture.meta.lens)
        arrayList.add(picture.date)
        arrayList.add("${picture.meta.coordinates?.latitude} , ${picture.meta.coordinates?.longitude}")
        data = arrayList.toList()
    }

}