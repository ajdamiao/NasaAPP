package com.example.nasaapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nasaapp.databinding.ActivityTelaDetalhesBinding
import com.example.nasaapp.model.NasaResponse
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.squareup.picasso.Picasso
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


        binding.toolbarDetalhes.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val data = intent.getStringExtra("date")

        if (data != null) {
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

/*
        binding.toolbarDetalhes.setNavigationOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        val date = intent.getStringExtra("dateIntent")
        val caption = intent.getStringExtra("captionIntent")
        val imagemPNG = intent.getStringExtra("imageIntent")
        val coordenates = intent.getStringExtra("coordenates")
        val nomeImagem = intent.getStringExtra("imageName")

        val lat = coordenates!!.substring(24, 32).toDouble()
        val long = coordenates!!.substring(38, 47).toDouble()
        val position = LatLng(lat, -long)

        val map = binding.mapView

        binding.txtDate.text = date
        binding.txtCaption.text = caption
        binding.txtCoordenates.text = coordenates
        binding.txtImageName.text = nomeImagem
        Picasso.get().load(imagemPNG).into(binding.image)


        map.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        googleMap?.apply {
            val position = LatLng(-143.679199, 7.119141)
            addMarker(
                MarkerOptions()
                    .position(position)
                    .title("Position")
            )
        }
        */
    }
}

