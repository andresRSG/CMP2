package com.example.projecttwo

import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.projecttwo.databinding.ActivityMoviesBinding
import com.example.projecttwo.util.Constants
import org.imaginativeworld.whynotimagecarousel.listener.CarouselListener
import org.imaginativeworld.whynotimagecarousel.model.CarouselGravity
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem
import org.imaginativeworld.whynotimagecarousel.model.CarouselType
import org.imaginativeworld.whynotimagecarousel.utils.dpToPx
import org.imaginativeworld.whynotimagecarousel.utils.spToPx

class MoviesActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMoviesBinding

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        val movies = bundle?.getStringArrayList("infoMovies")

        val images = movies?.let { checkImagesMovies(it) }
        val titles = movies?.let { checkTitlesMovies(it) }


        binding.carousel1.apply {
            registerLifecycle(lifecycle)
            showTopShadow = true
            topShadowAlpha = 0.15f // 0 to 1, 1 means 100%
            topShadowHeight = 32.dpToPx(context) // px value of dp

            showBottomShadow = true
            bottomShadowAlpha = 0.6f // 0 to 1, 1 means 100%
            bottomShadowHeight = 64.dpToPx(context) // px value of dp

            showCaption = true
            captionMargin = 0.dpToPx(context) // px value of dp
            captionTextSize = 14.spToPx(context) // px value of sp

            showIndicator = true
            indicatorMargin = 0.dpToPx(context) // px value of dp

            imageScaleType = ImageView.ScaleType.CENTER_CROP

            carouselBackground = ColorDrawable(getColor(R.color.white))
            imagePlaceholder = ContextCompat.getDrawable(
                this@MoviesActivity,
                org.imaginativeworld.whynotimagecarousel.R.drawable.carousel_default_placeholder
            )

            carouselPadding = 0.dpToPx(context)
            carouselPaddingStart = 0.dpToPx(context)
            carouselPaddingTop = 0.dpToPx(context)
            carouselPaddingEnd = 0.dpToPx(context)
            carouselPaddingBottom = 0.dpToPx(context)

            showNavigationButtons = true
            previousButtonLayout =
                org.imaginativeworld.whynotimagecarousel.R.layout.previous_button_layout
            previousButtonId =
                org.imaginativeworld.whynotimagecarousel.R.id.btn_previous
            previousButtonMargin = 4.dpToPx(context) // px value of dp
            nextButtonLayout =
                org.imaginativeworld.whynotimagecarousel.R.layout.next_button_layout
            nextButtonId = org.imaginativeworld.whynotimagecarousel.R.id.btn_next
            nextButtonMargin = 4.dpToPx(context) // px value of dp

            carouselType = CarouselType.BLOCK

            carouselGravity = CarouselGravity.CENTER

            scaleOnScroll = false
            scalingFactor = .15f // 0 to 1; 1 means 100
            autoWidthFixing = true
            autoPlay = true
            autoPlayDelay = 3000 // Milliseconds
            infiniteCarousel = true
            touchToPause = true

            carouselListener = object : CarouselListener {
                override fun onClick(position: Int, carouselItem: CarouselItem) {
                    Toast.makeText(
                        this@MoviesActivity,
                        "${carouselItem.headers?.get("header_key$position")}.",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }

                override fun onLongClick(position: Int, carouselItem: CarouselItem) {}
            }
        }

        movies?.let {
            if(it.size == 1) {
             binding.carousel1.stop()
             binding.carousel1.autoPlay = false
             binding.carousel1.infiniteCarousel = false

            }
        }


        // Dummy header
        val headers = mutableMapOf<String, String>()
        titles?.let {
            for (item in it){
                headers["header_key${titles.indexOf(item)}"] = item
            }
        }

        val listOne = mutableListOf<CarouselItem>()
        if (images != null) {
            for (item in images){
              listOne.add(
                  CarouselItem(
                  imageUrl = images[images.indexOf(item)],
                  caption = getString(R.string.image_of, images.indexOf(item) + 1 , images.size),
                      headers = headers
              ))
            }
        }

        binding.carousel1.setData(listOne)

    }

    private fun checkTitlesMovies(movies:ArrayList<String>):ArrayList<String>
    {
        val listTitles = ArrayList<String>()

        for (item in movies){
            val aux = item.split("/")
            println(aux)
            Log.d(Constants.LOGTAG, "Aux Title: $aux")

            when(aux[5].toInt()){
                1 ->
                    listTitles.add(getString(R.string.starw_title1))
                2 ->
                    listTitles.add(getString(R.string.starw_title2))
                3 ->
                    listTitles.add(getString(R.string.starw_title3))
                4 ->
                    listTitles.add(getString(R.string.starw_title4))
                5 ->
                    listTitles.add(getString(R.string.starw_title5))
                6 ->
                    listTitles.add(getString(R.string.starw_title6))
            }

        }
        return listTitles
    }


    private fun checkImagesMovies(movies:ArrayList<String>):ArrayList<String>
    {
        val listImages = ArrayList<String>()

        for (item in movies){
            val aux = item.split("/")
            println(aux)
            Log.d(Constants.LOGTAG, "Aux Images: $aux")

            when(aux[5].toInt()){
                1 ->
                    listImages.add(getString(R.string.starw_movie1))
                2 ->
                    listImages.add(getString(R.string.starw_movie2))
                3 ->
                    listImages.add(getString(R.string.starw_movie3))
                4 ->
                    listImages.add(getString(R.string.starw_movie4))
                5 ->
                    listImages.add(getString(R.string.starw_movie5))
                6 ->
                    listImages.add(getString(R.string.starw_movie6))

            }

        }
        return listImages
    }


}


