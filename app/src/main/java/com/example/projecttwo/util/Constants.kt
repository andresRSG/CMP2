package com.example.projecttwo.util

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



object Constants {
    const val BASE_URL = "https://swapi.dev/" //Debe terminar en /

    const val ID_MOVIES = "infoMovies"
    const val ID_PLANET = "infoPlanet"
    const val LOGTAG = "LOGS"

    fun getRetrofit(): Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

}