package com.example.fdbsilverfilm.view

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.fdbsilverfilm.R
import com.example.fdbsilverfilm.model.Globals
import com.example.fdbsilverfilm.model.Picture
import com.example.fdbsilverfilm.viewmodel.PictureDetailsViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class PictureDetailsActivity : AppCompatActivity(), OnMapReadyCallback {
    private var vm: PictureDetailsViewModel? = null;

    private var mapView: MapView? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture_details)

        val picture = intent.getSerializableExtra(Globals.PICTURE_EXTRA_TAG) as Picture
        vm = PictureDetailsViewModel(picture)

        // [ title, focal, opening, laying time, mode, lens, date, coordinates ]
        val textViews = ArrayList<TextView>()
        textViews.add(findViewById(R.id.picture_details_title))
        textViews.add(findViewById(R.id.focal_value_label))
        textViews.add(findViewById(R.id.opening_value_label))
        textViews.add(findViewById(R.id.laying_time_value_label))
        textViews.add(findViewById(R.id.mode_value_label))
        textViews.add(findViewById(R.id.lens_value_label))
        textViews.add(findViewById(R.id.date_value_label))
        textViews.add(findViewById(R.id.coordinates_value_label))

        for (i in 0 until textViews.size) {
            textViews[i].text =
                if (vm?.data?.get(i).isNullOrEmpty()) getString(R.string.NR) else vm?.data?.get(i)
        }

        mapView = findViewById(R.id.pictureDetailMapView)
        initGoogleMap(savedInstanceState)

        if (picture.preview.isNotEmpty()){
            val picturePreview = findViewById<ImageView>(R.id.picture_details_preview)
            picturePreview.setImageBitmap(Globals.stringToBitmap(picture.preview))
        }

    }

    private fun initGoogleMap(savedInstanceState: Bundle?) {
        val mapViewBundle: Bundle? = savedInstanceState?.getBundle(Globals.MAPVIEW_BUNDLE_KEY)

        mapView?.onCreate(mapViewBundle)
        mapView?.getMapAsync(this)
    }

    override fun onStart() {
        super.onStart()
        mapView?.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView?.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView?.onPause()
    }

    override fun onStop() {
        super.onStop()
        mapView?.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView?.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)

        var mapViewBundle: Bundle? = outState.getBundle(Globals.MAPVIEW_BUNDLE_KEY)
        if (mapViewBundle == null) {
            mapViewBundle = Bundle()
            outState.putBundle(Globals.MAPVIEW_BUNDLE_KEY, mapViewBundle)
        }

        mapView?.onSaveInstanceState(mapViewBundle)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView?.onLowMemory()
    }

    override fun onMapReady(map: GoogleMap) {
        val latLng = vm?.picture?.meta?.latitude?.let {
            vm?.picture?.meta?.longitude?.let { it1 ->
                LatLng(
                    it,
                    it1
                )
            }
        }
        if (latLng != null) {
            map.addMarker(
                MarkerOptions().position(latLng).title(vm?.picture?.title)
            )

            map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10f))

        }

    }
}


