package com.pietroluongo.animalfacts

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executors

class CatViewModel: ViewModel() {
    private var catUrl = MutableLiveData<String>()
    private var catImage = MutableLiveData<Bitmap>()

    private val bpService = ClientRetrofit.createService(CatService::class.java)

    fun getImageUrl(): LiveData<String> {
        return catUrl
    }

    fun getCatImage(): LiveData<Bitmap> {
        return catImage
    }

    fun fetchCat() {
        Log.d("Cat fetch", "fetching....")
        val listCall = bpService.getCats()


        listCall.enqueue(object: Callback<List<CatEntity>> {
            override fun onResponse(
                call: Call<List<CatEntity>>,
                response: Response<List<CatEntity>>
            ) {
                val list = response.body()
                Log.d("Success", "Got following response: ${response.body()}")
                Log.d("Success", "Got following call: ${call.request()}")
                catUrl.value = response.body()?.get(0)?.url;
                downloadCatImage()
            }

            override fun onFailure(call: Call<List<CatEntity>>, t: Throwable) {
                Log.e("Failed", "Failed to fetch - ${t.cause}")
                Log.e("Failed", "Failed to fetch call - ${call.request()}")
            }
        })
    }

    private fun downloadCatImage() {
        val executor = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())
        executor.execute {
            try {
                var image: Bitmap? = null

                val `in` = java.net.URL(catUrl.value).openStream()
                image = BitmapFactory.decodeStream(`in`)
                handler.post {
                    catImage.value = image
                }
            }
            catch(e: java.lang.Exception) {
                Log.e("Cat Error", "Failed to fetch cat data!")
            }   
        }
    }




}