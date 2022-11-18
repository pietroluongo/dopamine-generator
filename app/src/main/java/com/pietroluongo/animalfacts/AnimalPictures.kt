package com.pietroluongo.animalfacts

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.util.Log.DEBUG
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.pietroluongo.animalfacts.databinding.ActivityAnimalPicturesBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executors

class AnimalPictures : AppCompatActivity(), View.OnClickListener {
    lateinit var binding: ActivityAnimalPicturesBinding

    private lateinit var catVM: CatViewModel

    private val bpService = ClientRetrofit.createService(CatService::class.java)
    private val listCall = bpService.getCats()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimalPicturesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        catVM = ViewModelProvider(this).get(CatViewModel::class.java)
        setObserver()

        binding.fetchAnimalButton.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when(view.id) {
            binding.fetchAnimalButton.id -> {
                Log.d("Cat", "clicked!")
                catVM.fetchCat()
            }
            else -> {

            }
        }
    }

    private fun setObserver() {
        catVM.getCatImage().observe(this, Observer {
            binding.imageView.setImageBitmap(it)
        })
    }

}