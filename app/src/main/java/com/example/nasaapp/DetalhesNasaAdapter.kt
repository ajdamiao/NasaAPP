package com.example.nasaapp

import android.content.Intent
import android.opengl.Visibility
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.nasaapp.databinding.ItemDetalhesBinding
import com.example.nasaapp.model.NasaResponse
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.squareup.picasso.Picasso
import java.util.*
import kotlin.concurrent.schedule
import kotlin.concurrent.timerTask

class DetalhesNasaAdapter(private val nasa: NasaResponse): RecyclerView.Adapter<DetalhesNasaAdapter.NasaViewHolder>(){
    inner class NasaViewHolder(val binding: ItemDetalhesBinding): RecyclerView.ViewHolder(binding.root)

    private var dateString: String? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NasaViewHolder {
        val binding = ItemDetalhesBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return NasaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NasaViewHolder, position: Int) {
       with(holder)
       {

           with(nasa[position])
           {

               dateString = date.substring(0,10).replace("-","/")
               Picasso.get().load("https://epic.gsfc.nasa.gov/archive/natural/$dateString/png/$image.png")
                       .into(binding.image)

               binding.txtImageName.text = image
               binding.txtDate.text = date
               binding.txtCaption.text = caption

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
                           Picasso.get().load("https://epic.gsfc.nasa.gov/archive/natural/$dateString/png/$image.png")
                                   .into(binding.image)

                           binding.txtImageName.text = image
                           binding.txtDate.text = date
                           binding.txtCaption.text = caption
                           binding.txtCoordenates.text = centroid_coordinates.toString()
                       }
                   }
               }


               /*binding.btnPlay.setOnClickListener {



                   timerFare.schedule(2000) {
                       trocarImagem()
                   }

                   /*with(nasa[i+1])
                   {
                       Picasso.get().load("https://epic.gsfc.nasa.gov/archive/natural/$dateString/png/$image.png")
                               .into(binding.image)
                       binding.txtImageName.text = image
                       binding.txtDate.text = date
                       binding.txtCaption.text = " "
                       binding.txtCoordenates.text = " "

                   }*/

               }*/
               binding.toolbarDetalhes.setNavigationOnClickListener {
                   val intent = Intent(itemView.context, MainActivity::class.java)
                   itemView.context.startActivity(intent)
               }
           }
       }
    }

    /*private fun NasaViewHolder.trocarImagem() {
        if(index < nasa.size-1)
        {
            index++
        }
        else
        {
            index = 0
        }
        with(nasa[index])
        {
            Picasso.get().load("https://epic.gsfc.nasa.gov/archive/natural/$dateString/png/$image.png")
                    .into(binding.image)
            binding.txtImageName.text = image
            binding.txtDate.text = date
            binding.txtCaption.text = " "
            binding.txtCoordenates.text = " "
        }
    }*/

    override fun getItemCount(): Int = nasa.size
}

