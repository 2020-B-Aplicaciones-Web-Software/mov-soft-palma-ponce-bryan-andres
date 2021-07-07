package com.example.practica1moviles

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn_ir_ciclo_vida = findViewById<Button>(
            R.id.btn_ir_ciclo_vida
        )
        btn_ir_ciclo_vida.setOnClickListener {
            abrirCicloVida(
                a_activity::class.java
            ) }
        val btn_ir_list_view=findViewById<Button>(
            R.id.btn_ir_list_view
        )
        btn_ir_list_view
            .setOnClickListener{
                abrirCicloVida(
                    b_activity::class.java
                )
            }

    }

    fun abrirCicloVida(
        clase: Class<*>
    ) {
        val intentExplicito = Intent(
            this,
            a_activity::class.java
        )
        startActivity(intentExplicito)
    }
}