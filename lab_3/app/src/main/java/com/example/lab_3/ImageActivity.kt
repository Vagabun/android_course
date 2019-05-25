package com.example.lab_3

import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_image.*

class ImageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        val photoPath = intent.getStringExtra(MainActivity.PHOTO_PATH)
        val photoLabel = intent.getStringExtra(MainActivity.PHOTO_LABEL)

        BitmapFactory.decodeFile(photoPath).also {
            imageView.setImageBitmap(it)
        }
        imageLabel.text = photoLabel
    }
}
