package com.example.examen1b

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        abrirVistaAutores(
            lista_autores::class.java
        )

    }


    fun abrirVistaAutores(
        clase: Class<*>
    ) {
        val intentExplicito = Intent(
            this,
            clase
        )
        startActivity(intentExplicito)
    }
}