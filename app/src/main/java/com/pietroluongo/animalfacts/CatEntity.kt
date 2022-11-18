package com.pietroluongo.animalfacts

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

class CatEntity {
    @SerializedName("id")
    var catId: String = ""

    @SerializedName("url")
    var url: String = ""

    @SerializedName("width")
    var width: String = ""

    @SerializedName("height")
    var height: String = ""

    override fun toString(): String {
        return Gson().toJson(this)
    }
}