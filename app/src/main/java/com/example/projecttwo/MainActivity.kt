package com.example.projecttwo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.projecttwo.databinding.ActivityMainBinding
import com.example.projecttwo.service.HarryApi
import com.example.projecttwo.service.Model
import com.example.projecttwo.util.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val call = Constants.getRetrofit().create(HarryApi::class.java).getPeople()

        CoroutineScope(Dispatchers.IO).launch {
            call.enqueue(object : Callback<Model>{
                override fun onResponse(
                    call: Call<Model>,
                    response: Response<Model>
                ) {
                    Log.d(Constants.LOGTAG, "Respuesta del servidor: ${response.toString()}")

                }

                override fun onFailure(call: Call<Model>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })

        }
    }
}