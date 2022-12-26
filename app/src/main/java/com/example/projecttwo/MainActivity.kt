package com.example.projecttwo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projecttwo.adapter.AdapterPeople
import com.example.projecttwo.databinding.ActivityMainBinding
import com.example.projecttwo.service.HarryApi
import com.example.projecttwo.service.InfoPerson
import com.example.projecttwo.service.PersonModel
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
            call.enqueue(object : Callback<PersonModel>{
                override fun onResponse(
                    call: Call<PersonModel>,
                    response: Response<PersonModel>
                ) {
                    Log.d(Constants.LOGTAG, "Respuesta del servidor: ${response.toString()}")
                    var peopleResponse = response.body()
                    var listInfoPerson: ArrayList<InfoPerson> = peopleResponse!!.people

                    binding.rvMenu.layoutManager = LinearLayoutManager(this@MainActivity)
                    binding.rvMenu.adapter = AdapterPeople(this@MainActivity, listInfoPerson)

                    binding.pbConexion.visibility = View.GONE

                }

                override fun onFailure(call: Call<PersonModel>, t: Throwable) {
                    println(call)
                }
            })

        }
    }
}