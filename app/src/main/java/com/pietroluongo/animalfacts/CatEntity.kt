package com.pietroluongo.animalfacts

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

class CatEntity {
    @SerializedName("id")
    val catId: String = ""

    @SerializedName("url")
    val url: String = ""

    @SerializedName("width")
    val width: String = ""

    @SerializedName("height")
    val height: String = ""

    override fun toString(): String {
        return Gson().toJson(this)
    }
}