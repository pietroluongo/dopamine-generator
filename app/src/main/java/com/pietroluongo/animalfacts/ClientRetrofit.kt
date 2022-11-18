package com.pietroluongo.animalfacts

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ClientRetrofit {
    companion object {
        private lateinit var CAT_INSTANCE: Retrofit
        private lateinit var DOG_INSTANCE: Retrofit
        private const val BASE_CAT_URL = "https://api.thecatapi.com/"
        private const val BASE_DOG_URL = "https://dog.ceo/"

        private fun getCatClientInstance(): Retrofit {
            val http = OkHttpClient.Builder()
            if (!::CAT_INSTANCE.isInitialized) {
                CAT_INSTANCE = Retrofit.Builder().baseUrl(BASE_CAT_URL).client(http.build())
                    .addConverterFactory(GsonConverterFactory.create()).build()
            }
            return CAT_INSTANCE
        }

        private fun getDogClientInstance(): Retrofit {
            val http = OkHttpClient.Builder()
            if (!::DOG_INSTANCE.isInitialized) {
                DOG_INSTANCE = Retrofit.Builder().baseUrl(BASE_DOG_URL).client(http.build())
                    .addConverterFactory(GsonConverterFactory.create()).build()
            }
            return DOG_INSTANCE
        }


        fun <S> createCatService(className: Class<S>): S {
            return getCatClientInstance().create(className)
        }

        fun <S> createDogService(className: Class<S>): S {
            return getDogClientInstance().create(className)
        }
    }


}