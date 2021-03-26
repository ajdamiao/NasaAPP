package com.example.nasaapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nasaapp.databinding.ActivityTelaDetalhesBinding
import com.example.nasaapp.model.NasaResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TelaDetalhes : AppCompatActivity(){
    private lateinit var binding: ActivityTelaDetalhesBinding

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTelaDetalhesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        binding.recyclerViewDetalhes.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)

        val data = intent.getStringExtra("date")

        if (data != null)
        {
            NasaRequest().makeRequest().getPhotos(data)
                    .enqueue(object : Callback<NasaResponse> {
                        override fun onResponse(
                                call: Call<NasaResponse>,
                                response: Response<NasaResponse>
                        ) {
                            val nasaResponse = response.body()

                            if (nasaResponse != null) {
                                binding.recyclerViewDetalhes.adapter = DetalhesNasaAdapter(nasaResponse)
                            }
                        }

                        override fun onFailure(call: Call<NasaResponse>, t: Throwable) {
                            println(t.message)
                        }
                    })
        }
    }
}

