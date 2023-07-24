package com.example.map

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager


/**
 * Created by nurseiit.tursunkulov on 24.07.2023
 * MyViewPager
 */
class MyViewPager @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ViewPager(context, attrs) {

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(motionEvent: MotionEvent): Boolean = try {
        Log.d("Nurs","viewPager onTochEvent ${motionEvent.mytoString()}")
        super.onTouchEvent(motionEvent)
    } catch (e: IllegalArgumentException) {
        Log.d("Nurs","onTouchEvent e $e" )
        false
    }

    override fun onInterceptTouchEvent(motionEvent: MotionEvent): Boolean = try {
        Log.d("Nurs","viewPager onInterceptTouchEvent ${motionEvent.mytoString()}")
        super.onInterceptTouchEvent(motionEvent)
    } catch (e: IllegalArgumentException) {
        Log.d("Nurs","onInterceptTouchEvent e $e" )
        false
    }

}
