package com.example.practiceapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class CIntentExplicitoParametros : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_c_intent_explicito_parametros)
        val nombre=intent.getStringExtra("nombre")
        val apellido=intent.getStringExtra("apellido")
        val edad=intent.getIntExtra("edad",0)
        val entrenador=intent.getParcelableExtra<BEntrenador>("Entrenador")


        Log.i("intentE","$nombre")
        Log.i("intentE","$apellido")
        Log.i("intentE","$edad")
        Log.i("intentE","$entrenador")

        val btn_devolver_respuesta=findViewById<Button>(R.id.btn_devolver_respuesta)
        btn_devolver_respuesta
            .setOnClickListener{
                val devolverParametros=Intent()
                devolverParametros.putExtra("NombreModificado","Bryan")
                devolverParametros.putExtra("EdadModificada",26)
                devolverParametros.putExtra("Entrenador",BEntrenador("Pedro","Perez"))
                setResult(Activity.RESULT_OK,devolverParametros)
                finish()
            }
    }

}