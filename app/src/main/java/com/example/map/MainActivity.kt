package com.example.map

import android.app.Dialog
import android.os.Bundle
import android.view.MotionEvent
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val openDialogButton: Button = findViewById(R.id.openDialogButton)
        openDialogButton.setOnClickListener {
            showImageSliderDialog()
        }
    }

    private fun showImageSliderDialog() {
        val dialogFragment = ImageSliderDialogFragment()
        dialogFragment.show(supportFragmentManager, ImageSliderDialogFragment::class.java.simpleName)
    }
}