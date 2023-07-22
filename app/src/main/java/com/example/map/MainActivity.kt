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
        val dialog = Dialog(this)
        val linearLayout = LinearLayout(this)
        linearLayout.orientation = LinearLayout.VERTICAL
        val btnB = Button(this)
        btnB.text = "ButtonB"
        linearLayout.addView(btnB, getLayoutParams())
        val btnA: Button = object : androidx.appcompat.widget.AppCompatButton(this) {
            override fun onTouchEvent(event: MotionEvent): Boolean {
                if (event.action == MotionEvent.ACTION_UP) {
//                    linearLayout.removeView(btnB)
                    dialog.dismiss()
                }
                return super.onTouchEvent(event)
            }
        }
        btnA.text = "ButtonA"
        linearLayout.addView(btnA, getLayoutParams())
        dialog.setContentView(linearLayout)
        dialog.show()
    }
    fun getLayoutParams(): LinearLayout.LayoutParams {
        return LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
    }
}