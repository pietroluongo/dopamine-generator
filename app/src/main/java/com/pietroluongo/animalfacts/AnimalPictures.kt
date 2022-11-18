package com.pietroluongo.animalfacts

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.pietroluongo.animalfacts.databinding.ActivityAnimalPicturesBinding

enum class Animal {
    Cat,
    Dog;

    companion object {
        fun fromString(str: String): Animal {
            Log.d("Animal conversion", str)
            return when (str.lowercase()) {
                "cat" -> Cat
                else -> Dog
            }
        }
    }
}

class AnimalPictures : AppCompatActivity(), View.OnClickListener {
    lateinit var binding: ActivityAnimalPicturesBinding
    private lateinit var animalVM: AnimalViewModel
    private lateinit var preferredAnimal: Animal

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimalPicturesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        animalVM = ViewModelProvider(this).get(AnimalViewModel::class.java)
        setObserver()

        preferredAnimal = Animal.fromString(AnimalPreferences(this).getString("ANIMAL_TYPE"))

        this.title = "Dopamine Generator - ${
            preferredAnimal.toString().replaceFirstChar { it.uppercase() }
        } mode"

        binding.fetchAnimalButton.setOnClickListener(this)

        val resourceId = this.resources.getIdentifier(preferredAnimal.toString().lowercase(), "drawable", this.packageName)
        binding.backgroundDefaultImage.setImageResource(resourceId)
        binding.backgroundDefaultImage.setColorFilter(ContextCompat.getColor(this, R.color.purple_700))
    }

    override fun onClick(view: View) {
        when (view.id) {
            binding.fetchAnimalButton.id -> {
                when (preferredAnimal) {
                    Animal.Cat ->
                        animalVM.fetchCat()
                    else ->
                        animalVM.fetchDog()
                }
            }
            else -> {

            }
        }
    }

    private fun setObserver() {
        animalVM.getAnimalImage().observe(this, Observer {
            binding.backgroundDefaultImage.alpha = 0.0f
            binding.imageView.setImageBitmap(it)
        })
    }

}