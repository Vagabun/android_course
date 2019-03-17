package com.example.lab_1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val toast = Toast.makeText(applicationContext, "onCreate activity called", Toast.LENGTH_LONG)
        toast.show()
    }

    override fun onResume() {
        super.onResume()
        val toast = Toast.makeText(applicationContext, "onResume activity called", Toast.LENGTH_LONG)
        toast.show()
    }

    override fun onPause() {
        super.onPause()
        val toast = Toast.makeText(applicationContext, "onPause activity called", Toast.LENGTH_LONG)
        toast.show()
    }

    override fun onStop() {
        super.onStop()
        val toast = Toast.makeText(applicationContext, "onStop activity called", Toast.LENGTH_LONG)
        toast.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        val toast = Toast.makeText(applicationContext, "onDestroy activity called", Toast.LENGTH_LONG)
        toast.show()
    }

    override fun onStart() {
        super.onStart()
        val toast = Toast.makeText(applicationContext, "onStart activity called", Toast.LENGTH_LONG)
        toast.show()
    }
}
