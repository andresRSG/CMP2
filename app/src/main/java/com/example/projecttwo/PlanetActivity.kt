package com.example.projecttwo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.projecttwo.databinding.ActivityPlanetBinding
import com.example.projecttwo.service.DetailPlanet
import com.example.projecttwo.service.HarryApi
import com.example.projecttwo.util.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlanetActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlanetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlanetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        val planetURL = bundle?.getString("infoPlanet")
        val planetArray = planetURL?.split("/")


        val call = Constants.getRetrofit().create(HarryApi::class.java).getPlanet(planetArray?.let{ it[5].toInt() })
        CoroutineScope(Dispatchers.IO).launch {
            call.enqueue(object :Callback<DetailPlanet>{
                override fun onResponse(
                    call: Call<DetailPlanet>,
                    response: Response<DetailPlanet>
                ) {
                    response.let {
                        Log.d(Constants.LOGTAG, "Respuesta del servidor Planet: ${it.toString()}")
                        val planetInfo = it.body()
                    }
                }

                override fun onFailure(call: Call<DetailPlanet>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })

        }

    }
}