package com.example.projecttwo.service

import com.google.gson.annotations.SerializedName

data class PersonModel (
    @SerializedName("results")
    var people:ArrayList<InfoPerson>
)


