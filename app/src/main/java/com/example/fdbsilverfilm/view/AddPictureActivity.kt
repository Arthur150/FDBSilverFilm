package com.example.fdbsilverfilm.view

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.fdbsilverfilm.R
import com.example.fdbsilverfilm.manager.SharedPreferencesManager
import com.example.fdbsilverfilm.model.Globals
import com.example.fdbsilverfilm.viewmodel.PictureAddViewModel
import java.io.ByteArrayOutputStream
import java.util.*


class AddPictureActivity : AppCompatActivity() {
    private lateinit var focal: EditText
    private lateinit var lens: EditText
    private lateinit var opening: EditText
    private lateinit var time: EditText
    private lateinit var title: EditText

    private lateinit var picture: ImageView
    private lateinit var picture2: ImageView

    val REQUEST_IMAGE_CAPTURE = 1


    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_picture)




        focal = findViewById(R.id.addPictureFocal)
        lens = findViewById(R.id.addPictureLens)
        opening = findViewById(R.id.addPictureOpening)
        time = findViewById(R.id.addPictureTime)
        title = findViewById(R.id.addPictureTitle)

        val modeSpinner = findViewById<Spinner>(R.id.addPictureMode)
        val button = findViewById<Button>(R.id.addPictureButton)

        picture = findViewById(R.id.imageView4)
        picture2 = findViewById(R.id.imageView5)


        val vm = PictureAddViewModel(intent.getIntExtra(Globals.FILM_EXTRA_TAG, -1), this)
        vm.loadFilm()

        ArrayAdapter.createFromResource(
            this,
            R.array.picture_mode,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            modeSpinner.adapter = adapter


            val metaSharedPreferences = SharedPreferencesManager.loadCurrentMeta(this)

            if (metaSharedPreferences != null) {
                focal.setText(metaSharedPreferences.focal.toString())
                lens.setText(metaSharedPreferences.lens)
                opening.setText(metaSharedPreferences.opening.toString())
                time.setText(metaSharedPreferences.time.toString())

                modeSpinner.setSelection(adapter.getPosition(metaSharedPreferences.mode))
            }
        }

        vm.getFilm().observe(this, {
            button.isEnabled = true
            button.setOnClickListener {

                val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                try {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                } catch (e: android.content.ActivityNotFoundException) {
                    // display error state to the user
                }


                /* if (checkForm()) {
                     val meta = Meta(
                         focal = focal.text.toString().toFloat(),
                         opening = opening.text.toString().toFloat(),
                         time = time.text.toString().toDouble(),
                         mode = modeSpinner.selectedItem.toString(),
                         lens = lens.text.toString(),
                         latitude = vm.location?.latitude?: 0.0,
                         longitude = vm.location?.longitude ?: 0.0
                     )

                     vm.addPicture(
                         pictureName = title.text.toString(),
                         meta = meta
                     )

                     //Save the last config Meta
                     SharedPreferencesManager.saveCurrentMeta(this, meta)

                     val intent = Intent(this, MainActivity::class.java)
                     startActivity(intent)
                     finish()
                 }*/


            }
        })
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap

            picture.setImageBitmap(imageBitmap)


            //code
            val byteArrayOutputStream = ByteArrayOutputStream()
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
            val byteArray = byteArrayOutputStream.toByteArray()
            val stringImage = Base64.getEncoder().encodeToString(byteArray)

            Toast.makeText(this, stringImage, Toast.LENGTH_LONG).show()

            //decode
            val imageBytes = Base64.getDecoder().decode(stringImage)
            val image = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)

            picture2.setImageBitmap(image)
        }
    }


    private fun checkForm(): Boolean {
        var isOk = true

        if (focal.text.isEmpty() || focal.text.isBlank()) {
            focal.error = getString(R.string.check_form_field)
            isOk = false
        } else {
            if (!Globals.regexDecimal(focal.text.toString())) {
                focal.error = getString(R.string.check_form_regex_decimal)
                isOk = false
            } else {
                focal.error = null
            }
        }

        if (opening.text.isEmpty() || opening.text.isBlank()) {
            opening.error = getString(R.string.check_form_field)
            isOk = false
        } else {
            if (!Globals.regexDecimal(opening.text.toString())) {
                opening.error = getString(R.string.check_form_regex_decimal)
                isOk = false
            } else {
                opening.error = null
            }
        }

        if (time.text.isEmpty() || time.text.isBlank()) {
            time.error = getString(R.string.check_form_field)
            isOk = false
        } else {
            if (!Globals.regexDecimal(time.text.toString())) {
                time.error = getString(R.string.check_form_regex_decimal)
                isOk = false
            } else {
                time.error = null
            }
        }

        if (lens.text.isEmpty() || lens.text.isBlank()) {
            lens.error = getString(R.string.check_form_field)
            isOk = false
        } else {
            lens.error = null
        }

        return isOk
    }
}

