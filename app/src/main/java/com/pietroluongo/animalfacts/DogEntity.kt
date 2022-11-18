package com.pietroluongo.animalfacts

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

class DogEntity {
    @SerializedName("message")
    val url: String = ""

    @SerializedName("status")
    val status: String = ""

    override fun toString(): String {
        return Gson().toJson(this)
    }
}