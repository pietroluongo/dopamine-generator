package com.pietroluongo.animalfacts

import android.content.Context
import android.content.SharedPreferences

class AnimalPreferences(context: Context) {
    private val sp: SharedPreferences = context.getSharedPreferences("ANIMAL_TYPE", Context.MODE_PRIVATE)


}