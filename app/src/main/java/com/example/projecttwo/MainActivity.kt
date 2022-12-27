package com.example.projecttwo

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.blogspot.atifsoftwares.animatoolib.Animatoo
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
                    if(response.body() != null){
                        Log.d(Constants.LOGTAG, "Respuesta del servidor: ${response.toString()}")
                        val peopleResponse = response.body()
                        val listInfoPerson: ArrayList<InfoPerson> = peopleResponse!!.people

                        binding.rvMenu.layoutManager = LinearLayoutManager(this@MainActivity)
                        binding.rvMenu.adapter = AdapterPeople(this@MainActivity, listInfoPerson)

                        binding.pbConexion.visibility = View.GONE
                        binding.tvEmpty.visibility = View.GONE

                    }else{
                        binding.pbConexion.visibility = View.GONE
                        binding.tvEmpty.visibility = View.VISIBLE
                    }
                }

                override fun onFailure(call: Call<PersonModel>, t: Throwable) {
                    binding.pbConexion.visibility = View.GONE
                    Log.d(Constants.LOGTAG, "Respuesta del servidor: ${call.toString()}")
                }
            })

        }
    }

    fun clickPerson(infoPlanet:String, infoMovie: ArrayList<String>){
        val dialogInfo = Dialog(this)
        dialogInfo.setContentView(R.layout.dialog_person)
        dialogInfo.window?.setBackgroundDrawableResource(R.drawable.drawable_bg_alert)
        val text = dialogInfo.findViewById<TextView>(R.id.text_description_dialog)
        text.setOnClickListener { dialogInfo.dismiss() }
        val imgMovie = dialogInfo.findViewById<ImageView>(R.id.imageMovie)
        val imgPlanet = dialogInfo.findViewById<ImageView>(R.id.imagePlanet)

        imgMovie.setOnClickListener {
            val intent = Intent(this@MainActivity, MoviesActivity::class.java)
            val parameters = Bundle()
            parameters.putStringArrayList("infoMovies",infoMovie)
            intent.putExtras(parameters)
            startActivity(intent)
            dialogInfo.dismiss()
            Animatoo.animateShrink(this@MainActivity)
        }

        imgPlanet.setOnClickListener {
            val intent = Intent(this@MainActivity, PlanetActivity::class.java)
            val parameters = Bundle()
            parameters.putString("infoPlanet", infoPlanet)
            intent.putExtras(parameters)
            startActivity(intent)
            dialogInfo.dismiss()
            Animatoo.animateShrink(this@MainActivity)
        }
        dialogInfo.show()

    }

    override fun onBackPressed() {
        super.onBackPressed()
        Animatoo.animateSlideDown(this@MainActivity) //fire the slide left animation
    }
}