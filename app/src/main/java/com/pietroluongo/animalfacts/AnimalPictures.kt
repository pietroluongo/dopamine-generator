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
    private lateinit var catVM: CatViewModel
    private lateinit var prefs: AnimalPreferences
    private lateinit var preferredAnimal: Animal

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimalPicturesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        catVM = ViewModelProvider(this).get(CatViewModel::class.java)
        setObserver()

        preferredAnimal = Animal.fromString(AnimalPreferences(baseContext).getString("ANIMAL_TYPE"))

        this.title = "Dopamine Machine - ${preferredAnimal.toString().capitalize()} mode"

        binding.fetchAnimalButton.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
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