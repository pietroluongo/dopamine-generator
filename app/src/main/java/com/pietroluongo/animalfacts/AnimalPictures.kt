package com.pietroluongo.animalfacts

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.pietroluongo.animalfacts.databinding.ActivityAnimalPicturesBinding
import java.util.concurrent.Executors

class AnimalPictures : AppCompatActivity() {
    lateinit var binding: ActivityAnimalPicturesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimalPicturesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val executor = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())

        val imageView = binding.imageView


        executor.execute {
            try {
                var image: Bitmap? = null

                val imgUrl = "https://cdn2.thecatapi.com/images/7UmFRG3pa.jpg"

                val `in` = java.net.URL(imgUrl).openStream()
                image = BitmapFactory.decodeStream(`in`)
                handler.post {
                    imageView.setImageBitmap(image)
                }
            }
            catch(e: java.lang.Exception) {
                Toast.makeText(baseContext, " $e", Toast.LENGTH_LONG).show()
            }
        }



    }
}