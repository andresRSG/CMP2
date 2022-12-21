package com.example.projecttwo.service

data class Model (
    var result:ResultApi? = null
)

class ResultApi{
    var name:String? = null
    var height: String? = null
    var birth_year: String? = null
    var gender: String? = null
    var homeworld: String? = null
    var films = listOf<String>()
}




