package com.example.map


/**
 * Created by nurseiit.tursunkulov on 22.07.2023
 * ImageSliderDialogFragment
 */
import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.github.chrisbanes.photoview.PhotoView
import kotlin.math.absoluteValue

class ImageSliderDialogFragment : DialogFragment() {

    private lateinit var viewPager: ViewPager


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_image_slider, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager = view.findViewById(R.id.viewPager)
        val adapter = ImageSliderAdapter(swipeToDismissListener = { dismiss()},{move->
//            viewPager.onTouchEvent(move)
        })
//        viewPager.
        viewPager.adapter = adapter
        adapter?.setData(listOf(
            "https://ctsassets1.check24.de/size=1024x/di=3/nfc=999/source=aHR0cHM6Ly9jZG4ud29ybGRvdGEubmV0L3QvMTAyNHg3NjgvY29udGVudC8wYy8zNi8wYzM2ZWQyOWFjZDQ1ZTZkNjYxYzJlZDZjOGFjMmNhMTgxMDU4ZTc0LmpwZWc=!d66fd7/picture.jpg",
            "https://ctsassets1.check24.de/size=1920x/di=3/nfc=999/source=aHR0cHM6Ly9pLmdpYXRhbWVkaWEuY29tL2kucGhwP3VpZD0yMDMyOTEmaz1NVFUxTkRBMk9UWXdNRUFVSGNRclcwR1ElMkIxRTJzYm9DcDRvbXEwMnA5S3NxUUZPQ041d2FFOVp2b2VYcUxVY2lHdVRlVVJaU0tDRjlDTWJnS3dDaUtzZ204SmtjdGxjb2twRnBuME1MME5KN2w0MEpKWVBHd2cxNW93!dfcbf3/picture.jpg",
            "https://ctsassets1.check24.de/size=1920x/di=3/nfc=999/source=aHR0cHM6Ly9pLmdpYXRhbWVkaWEuY29tL2kucGhwP3VpZD0yMDMyOTEmaz1NVFUxTkRBMk9UWXdNRUFVSGNRclcwR1ElMkIxRTJzYm9DcDRvbXEwMnA5S3NxUUZPQ041d2FFOVp2b2NIVDNpSHg4NTY4NDk0anAyeDhFZXU0dDBGUG13OTZRdTdrdk1HSHVNNXNQJTJCZ2IwN0dMUU5oWlhEbWpaOHA1RGc=!279436/picture.jpg",
            "https://ctsassets1.check24.de/size=1000x/di=3/nfc=999/source=aHR0cDovL2Nkbi5zbXlyb29tcy5jb20vY2xvdWRjb250ZW50L2ZvdG9zL2FncmVnYWRvckhvdGVsZXJvLzAwMDEvNzUxMjQvMTc1MTI0LzY3LmpwZw==!ed973b/picture.jpg"
        ))
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

    companion object {

        private const val DOUBLE_TAP_INTERVAL: Long = 400
        private const val SWIPE_TO_DISMISS_Y = 500f

    }
    private inner class ImageSliderAdapter( private val swipeToDismissListener: OnSwipeToDismissListener?,val onDrag:OnDrag) : PagerAdapter() {
        private val data = mutableListOf<String>()
        fun setData(data: List<String>?) {
            log("setData")
            val notify = this.data.isNotEmpty()
//            this.data.clear()
            if (notify) {
                notifyDataSetChanged()
            }
            if (!data.isNullOrEmpty()) {
                this.data.addAll(data)
            }
            notifyDataSetChanged()
        }
        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val inflater = LayoutInflater.from(container.context)
            val itemView = inflater.inflate(R.layout.travel_item_slideshow, container, false)

            val dragView = itemView.findViewById<View>(R.id.dragView)
            val imagePreview = itemView.findViewById<PhotoView>(R.id.imagePreview)
            data.getOrNull(position)
                ?.let { imagePath ->
//                    Log.d("Nurs", "imagePath $imagePath")
                    Glide
                        .with(itemView)
                        .load(imagePath)
                        .centerCrop()
//                        .placeholder(R.drawable.loading_spinner)
                        .into(imagePreview);

                }

            dragView.setOnTouchListener(object : View.OnTouchListener {
                var startY = 0f

                @SuppressLint("ClickableViewAccessibility")
                override fun onTouch(v: View, event: MotionEvent): Boolean {
//                    if (!gestureDetector.onTouchEvent(event)) {
                    when (event.action) {
                        MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN ->{
//                            Log.d("Nurs","onTouch  MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN")
                            startY = event.y
                        }
                        MotionEvent.ACTION_MOVE -> {
//                                Log.d("Nurs","MotionEvent.ACTION_MOVE")
                            imagePreview.translationY = if (imagePreview.scale.isLessThanOrEqualTo(1f)) event.y - startY else 0f
                            if (imagePreview.translationY.absoluteValue.isGreaterThan(SWIPE_TO_DISMISS_Y)) {
                                Log.d("Nurs","                  swipeToDismissListener?.onCloseFragment()")
                                swipeToDismissListener?.onCloseFragment()
                            }
                        }
                        MotionEvent.ACTION_UP, MotionEvent.ACTION_POINTER_UP ->{
//                            Log.d("Nurs","MotionEvent.ACTION_UP, MotionEvent.ACTION_POINTER_UP")
                            imagePreview.translationY = 0f
                        }
                    }
//                        Log.d("Nurs","imagePreview.dispatchTouchEvent $event")
                    imagePreview.dispatchTouchEvent(event.apply { setLocation(event.x, 0f) })
//                    }

                    return true // dispatch another touch event to the image view, because we're consuming this one!
                }
            })
            Log.d("Nurs"," add view $itemView")


            container.addView(itemView)
            return itemView
        }

        override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
            container.removeView(obj as View)

            log("remove ${obj}")
        }

        override fun getCount(): Int {
            return data.size
        }

        override fun isViewFromObject(view: View, obj: Any): Boolean {
            return view == obj
        }
    }
}

fun interface OnSwipeToDismissListener {
    fun onCloseFragment()
}

fun interface OnDrag{
    fun drag(event: MotionEvent)
}

fun MotionEvent.mytoString():String{
    val msg = StringBuilder()
    msg.append("MotionEvent { action=").append(MotionEvent.actionToString(action))
    msg.append(" }")
    return msg.toString()
}

fun Float?.isLessThanOrEqualTo(compareValue: Float): Boolean = this != null && this <= compareValue
fun Float?.isGreaterThan(compareValue: Float): Boolean = this != null && this > compareValue

fun log(text:String){
    Log.d("Nurs",text)
}