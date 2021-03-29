package com.example.nasaapp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nasaapp.databinding.ItemFotoBinding
import com.example.nasaapp.model.NasaResponse
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import java.io.Serializable
import kotlin.math.sign

class NasaAdapter(private val nasa: NasaResponse): RecyclerView.Adapter<NasaAdapter.NasaViewHolder>() {
    inner class NasaViewHolder(val binding: ItemFotoBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NasaViewHolder {
        val binding = ItemFotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return NasaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NasaViewHolder, position: Int) {

        with(holder) {
            with(nasa[position]) {
                // Texto e Imagem
                binding.txtCaption.text = caption
                binding.txtNomeArquivo.text = image

                val dateString = date.substring(0, 10).replace("-", "/")
                Picasso.get().load("https://epic.gsfc.nasa.gov/archive/natural/$dateString/thumbs/$image.jpg").into(binding.imgThumb)

                binding.btnDetalhes.setOnClickListener {
                    val intent = Intent(itemView.context, TelaDetalhes::class.java)

                    val json = Gson().toJson(nasa[position])
                    intent.putExtra("nasaJson", json)


                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    override fun getItemCount(): Int = nasa.size
}