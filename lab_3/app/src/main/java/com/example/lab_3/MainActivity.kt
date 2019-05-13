package com.example.lab_3

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import kotlinx.android.synthetic.main.activity_main.*
import android.support.design.widget.Snackbar
import android.support.v4.content.FileProvider
import android.util.Log
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    companion object {
        val PHOTO_PATH = "PHOTO_PATH"
        val PHOTO_LABEL = "PHOTO_LABEL"
    }

    private val REQUEST_PHOTO_CAPTURE: Int = 1
    private var currentPhotoPath: String? = null

    @Throws(IOException::class)
    private fun createPhotoFile(): File {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ENGLISH).format(Date())
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
//        return try {
//            File.createTempFile(
//                "JPEG_${timeStamp}_",
//                ".jpg",
//                storageDir
//            ).apply {
//                currentPhotoPath = this.absolutePath
//            }
//        }
//        catch (e: IOException) {
//            throw e
//        }
        return File.createTempFile(
            "JPEG_${timeStamp}_",
            ".jpg",
            storageDir
        ).apply {
            currentPhotoPath = absolutePath
        }
    }

    private fun dispatchTakePhotoIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePhotoIntent ->
            //packageManager?
            takePhotoIntent.resolveActivity(packageManager)?.also {
                val photoFile: File? = try {
                    createPhotoFile()
                } catch (e: IOException) {
                    //Snackbar.make(mainView, "Error: can't create photo file", Snackbar.LENGTH_LONG).show()
                    Log.v("fileError", "something happened while creating the file")
                    null
                }
                photoFile?.also {
                    val photoURI: Uri = FileProvider.getUriForFile(
                        this, "com.example.android.fileprovider", it
                    )
                    takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePhotoIntent, REQUEST_PHOTO_CAPTURE)
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if ((requestCode == REQUEST_PHOTO_CAPTURE) and (resultCode == RESULT_OK)) {
            Intent(this, ImageActivity::class.java).also { imageActivityIntent ->
                imageActivityIntent.putExtra(PHOTO_PATH, currentPhotoPath)
                imageActivityIntent.putExtra(PHOTO_LABEL, nameInput.text.toString())

                startActivity(imageActivityIntent)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        makePhoto.setOnClickListener { v ->
            if (nameInput.text.isEmpty())
                Snackbar.make(v, "Name can't be empty", Snackbar.LENGTH_LONG).show()
//                Snackbar.make(mainView, "Name can't be empty", Snackbar.LENGTH_LONG).show()
            else
                dispatchTakePhotoIntent()
        }
    }

}
