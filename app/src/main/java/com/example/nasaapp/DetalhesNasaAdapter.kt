package com.example.nasaapp

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.nasaapp.databinding.ItemDetalhesBinding
import com.example.nasaapp.model.NasaResponse
import com.squareup.picasso.Picasso

class DetalhesNasaAdapter(private val nasa: NasaResponse): RecyclerView.Adapter<DetalhesNasaAdapter.NasaViewHolder>() {
    inner class NasaViewHolder(val binding: ItemDetalhesBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NasaViewHolder {
        val binding = ItemDetalhesBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return NasaViewHolder(binding)
    }



    override fun onBindViewHolder(holder: NasaViewHolder, position: Int) {
       with(holder)
       {

           with(nasa[position])
           {

               val dateString = date.substring(0,10).replace("-","/")
               Picasso.get().load("https://epic.gsfc.nasa.gov/archive/natural/$dateString/png/$image.png")
                       .into(binding.image)

               var posicaoAtual = nasa[position].toString().toInt()

               binding.txtImageName.text = image
               binding.txtDate.text = date
               binding.txtCaption.text = caption
               binding.txtCoordenates.text = centroid_coordinates.toString()

               binding.btnNext.setOnClickListener {
                   posicaoAtual = posicaoAtual++
                   with(posicaoAtual)
                   {
                       val dateString = date.substring(0,10).replace("-","/")
                       Picasso.get().load("https://epic.gsfc.nasa.gov/archive/natural/$dateString/png/$image.png")
                               .into(binding.image)

                       binding.txtImageName.text = image
                       binding.txtDate.text = date
                       binding.txtCaption.text = caption
                       binding.txtCoordenates.text = centroid_coordinates.toString()
                   }
               }
               binding.btnPreview.setOnClickListener {
                   posicaoAtual = posicaoAtual--
                   with(posicaoAtual)
                   {
                       val dateString = date.substring(0,10).replace("-","/")
                       Picasso.get().load("https://epic.gsfc.nasa.gov/archive/natural/$dateString/png/$image.png")
                               .into(binding.image)

                       binding.txtImageName.text = image
                       binding.txtDate.text = date
                       binding.txtCaption.text = caption
                       binding.txtCoordenates.text = centroid_coordinates.toString()
                   }
               }

               binding.btnPreview.setOnClickListener {
                   Toast.makeText(itemView.context, "OASDHIPUAHUPDHSIOUADSHOSHUHUSDAI", Toast.LENGTH_SHORT).show()
               }
           }
       }
    }

    override fun getItemCount(): Int = nasa.size
}