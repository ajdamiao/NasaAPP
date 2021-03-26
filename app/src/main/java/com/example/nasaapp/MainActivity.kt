package com.example.nasaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nasaapp.databinding.ActivityMainBinding
import com.example.nasaapp.model.NasaResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar!!.hide()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rcvPhotos.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)

        binding.btnBuscar.setOnClickListener {
            val data = binding.txtData.text.toString()

            NasaRequest().makeRequest().getPhotos(data)
                .enqueue(object : Callback<NasaResponse> {
                    override fun onResponse(
                        call: Call<NasaResponse>,
                        response: Response<NasaResponse>
                    ) {
                        val nasaResponse = response.body()

                        if (nasaResponse != null) {
                            binding.rcvPhotos.adapter = NasaAdapter(nasaResponse)
                        }
                    }

                    override fun onFailure(call: Call<NasaResponse>, t: Throwable) {
                        println(t.message)
                    }
                })
        }
    }
}