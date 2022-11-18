package com.pietroluongo.animalfacts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
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
    private lateinit var prefs: AnimalPreferences
    private lateinit var preferredAnimal: Animal

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimalPicturesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        animalVM = ViewModelProvider(this).get(AnimalViewModel::class.java)
        setObserver()

        preferredAnimal = Animal.fromString(AnimalPreferences(baseContext).getString("ANIMAL_TYPE"))

        this.title = "Dopamine Machine - ${preferredAnimal.toString().capitalize()} mode"

        binding.fetchAnimalButton.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            binding.fetchAnimalButton.id -> {
                when(preferredAnimal) {
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
            binding.imageView.setImageBitmap(it)
        })
    }

}