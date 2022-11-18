package com.pietroluongo.animalfacts

import android.content.Context
import android.content.SharedPreferences

class AnimalPreferences(context: Context) {
    private val sp: SharedPreferences = context.getSharedPreferences("ANIMAL_TYPE", Context.MODE_PRIVATE)

    fun setString(key: String, str: String) {
        sp.edit().putString(key, str).apply()
    }

    fun getString(key: String): String {
        return sp.getString(key, "") ?: ""
    }

}