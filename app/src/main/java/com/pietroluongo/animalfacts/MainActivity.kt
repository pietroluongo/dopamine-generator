package com.pietroluongo.animalfacts

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.pietroluongo.animalfacts.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var binding: ActivityMainBinding
    lateinit var prefs: AnimalPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prefs = AnimalPreferences(baseContext)

        binding.catsButton.setOnClickListener(this)
        binding.dogsButton.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            binding.catsButton.id -> {
                prefs.setString("ANIMAL_TYPE", "cat")
                startActivity(Intent(this, AnimalPictures::class.java))
            }
            binding.dogsButton.id -> {
                prefs.setString("ANIMAL_TYPE", "dog")
                startActivity(Intent(this, AnimalPictures::class.java))
            }
            else -> {}
        }
    }

}