package com.pietroluongo.animalfacts

import retrofit2.Call
import retrofit2.http.GET


interface DogService {
    @GET("/api/breeds/image/random")
    fun getDog(): Call<DogEntity>
}