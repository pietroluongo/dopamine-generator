package com.pietroluongo.animalfacts

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executors

class AnimalViewModel: ViewModel() {
    private var animalURL = MutableLiveData<String>()
    private var animalImage = MutableLiveData<Bitmap>()

    private val catService = ClientRetrofit.createCatService(CatService::class.java)
    private val dogService = ClientRetrofit.createDogService(DogService::class.java)

    fun getImageUrl(): LiveData<String> {
        return animalURL
    }

    fun getAnimalImage(): LiveData<Bitmap> {
        return animalImage
    }

    fun fetchCat() {
        val listCall = catService.getCats()

        listCall.enqueue(object: Callback<List<CatEntity>> {
            override fun onResponse(
                call: Call<List<CatEntity>>,
                response: Response<List<CatEntity>>
            ) {
                val list = response.body()
                Log.d("Success", "Got following response: ${response.body()}")
                Log.d("Success", "Got following call: ${call.request()}")
                animalURL.value = response.body()?.get(0)?.url;
                downloadImage()
            }

            override fun onFailure(call: Call<List<CatEntity>>, t: Throwable) {
                Log.e("Failed", "Failed to fetch - ${t.cause}")
                Log.e("Failed", "Failed to fetch call - ${call.request()}")
            }
        })
    }

    fun fetchDog() {
        val listCall = dogService.getDog()
        listCall.enqueue(object: Callback<DogEntity> {
            override fun onResponse(
                call: Call<DogEntity>,
                response: Response<DogEntity>
            ) {
                val list = response.body()
                Log.d("Success", "Got following response: ${response.body()}")
                Log.d("Success", "Got following call: ${call.request()}")
                animalURL.value = response.body()?.url;
                downloadImage()
            }

            override fun onFailure(call: Call<DogEntity>, t: Throwable) {
                Log.e("Failed", "Failed to fetch - ${t.cause}")
                Log.e("Failed", "Failed to fetch call - ${call.request()}")
            }
        })

    }

    private fun downloadImage() {
        val executor = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())
        executor.execute {
            try {
                var image: Bitmap? = null

                val `in` = java.net.URL(animalURL.value).openStream()
                image = BitmapFactory.decodeStream(`in`)
                handler.post {
                    animalImage.value = image
                }
            }
            catch(e: java.lang.Exception) {
                Log.e("Cat Error", "Failed to fetch cat data!")
            }
        }
    }
}