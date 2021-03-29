package com.example.nasaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class TelaTransicao : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_transicao)

        supportActionBar!!.hide()

        Handler(Looper.getMainLooper()).postDelayed({
            abrirTelaPrincipal()
        },2000)
    }

    fun abrirTelaPrincipal()
    {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}