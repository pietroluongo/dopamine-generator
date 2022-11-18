package com.pietroluongo.animalfacts

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.util.Log.DEBUG
import android.widget.Toast
import com.pietroluongo.animalfacts.databinding.ActivityAnimalPicturesBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executors

class AnimalPictures : AppCompatActivity() {
    lateinit var binding: ActivityAnimalPicturesBinding

    private val bpService = ClientRetrofit.createService(CatService::class.java)
    private val listCall = bpService.getCats()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimalPicturesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val executor = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())

        val imageView = binding.imageView

        listCall.enqueue(object: Callback<List<CatEntity>> {
            override fun onResponse(
                call: Call<List<CatEntity>>,
                response: Response<List<CatEntity>>
            ) {
                val list = response.body()
                Log.d("Success", "Got following response: ${response.body()}")
                Log.d("Success", "Got following call: ${call.request()}")
            }

            override fun onFailure(call: Call<List<CatEntity>>, t: Throwable) {
                Log.e("Failed", "Failed to fetch - ${t.cause}")
                Log.e("Failed", "Failed to fetch call - ${call.request()}")
            }
        })


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