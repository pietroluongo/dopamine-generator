package com.pietroluongo.animalfacts

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CatService {

    @GET("/v1/images/search/")
    fun getCats(): Call<List<CatEntity>>

    @GET("cats/{amount}")
    fun getNCats(@Path("amountId") catCount: Int): Call<List<CatEntity>>
}