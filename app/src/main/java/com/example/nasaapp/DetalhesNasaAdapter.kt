package com.example.nasaapp

import android.opengl.Visibility
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

               binding.txtImageName.text = image
               binding.txtDate.text = date
               binding.txtCaption.text = caption
               binding.txtCoordenates.text = centroid_coordinates.toString()

               var posicao = position
                // botao para proxima imagem
               binding.btnNext.setOnClickListener {
                   posicao += 1
                   if(posicao == nasa.size)
                   {
                       posicao = 0
                   }
                   else //Caso esteja na ultima foto o programa retornara para a primeira
                   {
                       println(nasa[posicao])
                       with(nasa[posicao])
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
               }
                // botao para imagem passada
               binding.btnPreview.setOnClickListener {
                   if(posicao == 0)
                   {
                       Toast.makeText(itemView.context, "Não é possivel voltar imagem", Toast.LENGTH_SHORT).show()
                   }
                   else //Caso caso seja a primeira foto o programa irá alertar q nao da para voltar
                   {
                       posicao -= 1
                       with(nasa[posicao])
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
               }
               binding.btnPlay.setOnClickListener {
                   var tamanho = nasa.size
                   tamanho -= 1
                   for(i in 0..2)
                   {
                       with(nasa[i+1])
                       {
                           Picasso.get().load("https://epic.gsfc.nasa.gov/archive/natural/$dateString/png/$image.png")
                                   .into(binding.image)
                           binding.txtImageName.text = image
                           binding.txtDate.text = date
                           binding.txtCaption.text = " "
                           binding.txtCoordenates.text = " "

                           Thread.sleep(2000)
                       }
                   }
               }
           }
       }
    }

override fun getItemCount(): Int = nasa.size
}