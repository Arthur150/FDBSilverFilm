package com.example.fdbsilverfilm.viewmodel

import com.example.fdbsilverfilm.model.Picture

class PictureDetailsViewModel(var picture: Picture) {

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
        arrayList.add("")
        data = arrayList.toList()
    }

}