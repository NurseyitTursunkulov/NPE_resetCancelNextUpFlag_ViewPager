package com.example.map


/**
 * Created by nurseiit.tursunkulov on 22.07.2023
 * ImageSliderDialogFragment
 */
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager

class ImageSliderDialogFragment : DialogFragment() {

    private lateinit var viewPager: ViewPager
    private val imageResources = intArrayOf(
        R.drawable.asdf_background,
        R.drawable.baseline_bluetooth_drive_24,
        R.drawable.baseline_connect_without_contact_24
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_image_slider, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager = view.findViewById(R.id.viewPager)
        val adapter = ImageSliderAdapter()
        viewPager.adapter = adapter
//        viewPager.setOnTouchListener { _, event ->
//        }
    }


    override fun onAttach(context: Context) {
        Log.d("Nurs","onAttach")
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
        log("onDetach")
    }

    private inner class ImageSliderAdapter : PagerAdapter() {

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val imageView = ImageView(requireContext())
            imageView.setImageResource(imageResources[position])
            container.addView(imageView)
            log("container $container  addView = $imageView")
            return imageView
        }

        override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
            log("container $container removeview = $obj")
            container.removeView(obj as View)
        }

        override fun getCount(): Int {
            return imageResources.size
        }

        override fun isViewFromObject(view: View, obj: Any): Boolean {
            return view == obj
        }
    }
}
fun log(text:String){
    Log.d("Nurs",text)
}