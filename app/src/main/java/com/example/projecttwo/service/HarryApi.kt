package com.example.projecttwo.service

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface HarryApi {

    //show my endpoint

    @GET("api/people/")
    fun getPeople(
    ): Call<PersonModel>

    @GET("api/planets/{id_Planet}/")
    fun getPlanet(
        @Path("id_Planet") id: Int?
    ): Call<DetailPlanet>


}