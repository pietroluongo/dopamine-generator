package com.pietroluongo.animalfacts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.pietroluongo.animalfacts.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.catsButton.setOnClickListener(this)
        binding.dogsButton.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when(view.id) {
            binding.catsButton.id -> {
                startActivity(Intent(this, AnimalPictures::class.java))
            }
            binding.dogsButton.id -> {
                startActivity(Intent(this, AnimalPictures::class.java))
            }
            else -> {}
        }
    }

}