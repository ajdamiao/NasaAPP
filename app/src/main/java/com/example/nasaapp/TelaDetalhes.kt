package com.example.nasaapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nasaapp.databinding.ActivityTelaDetalhesBinding
import com.example.nasaapp.model.NasaResponse
import com.example.nasaapp.model.NasaResponseItem
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable

class TelaDetalhes : AppCompatActivity(), OnMapReadyCallback{
    private lateinit var binding: ActivityTelaDetalhesBinding
    private var mMap: GoogleMap? = null
    private lateinit var nasaItem : NasaResponseItem

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTelaDetalhesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()

        binding.toolbarDetalhes.setNavigationOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val nasaJson = intent.getStringExtra("nasaJson")
        nasaItem = Gson().fromJson(nasaJson, NasaResponseItem::class.java)

        binding.txtImageName.text = nasaItem.image
        binding.txtDate.text = nasaItem.date
        binding.txtCaption.text = nasaItem.caption
        binding.txtCoordenates.text = nasaItem.centroid_coordinates.toString()

        val image = nasaItem.image

        val dateString = nasaItem.date.substring(0, 10).replace("-", "/")
        Picasso.get().load("https://epic.gsfc.nasa.gov/archive/natural/$dateString/png/$image.png").into(binding.image)

        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        if (googleMap != null) {
            mMap = googleMap
        }
        mMap?.addMarker(
                MarkerOptions()
                        .position(LatLng(nasaItem.centroid_coordinates.lat,nasaItem.centroid_coordinates.lon))
                        .title("Position")
        )
        mMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(nasaItem.centroid_coordinates.lat,nasaItem.centroid_coordinates.lon), 0.3F))
    }
}

