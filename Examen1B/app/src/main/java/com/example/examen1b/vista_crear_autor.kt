package com.example.examen1b

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class vista_crear_autor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vista_crear_autor)

        findViewById<Button>(R.id.btn_cancelar).setOnClickListener {
            startActivity(
                Intent(
                    this,
                    lista_autores::class.java
                )
            )
        }


        val btn_anadir_autores = findViewById<Button>(
            R.id.btn_guardar_autor
        )
        btn_anadir_autores.setOnClickListener {
            val nombre = findViewById<TextInputEditText>(R.id.nombre_autor).text.toString()
            val pais = findViewById<TextInputEditText>(R.id.pais_nac).text.toString()
            val fecha = findViewById<TextInputEditText>(R.id.fecha_nac).text.toString()
            val toast = Toast.makeText(applicationContext, nombre, Toast.LENGTH_SHORT)
            val arrayAutor = arrayListOf(nombre, pais, fecha)
            if ((arrayAutor.any { it == "" })) {
                toast.setText("Por favor rellene los campos")
                toast.show()
            } else {
                val conexionEscritura = Firebase.firestore
                val autor = hashMapOf(
                    "nombre" to nombre,
                    "fecha" to fecha,
                    "pais" to pais
                )
                conexionEscritura.collection("autor")
                    .add(autor)
                    .addOnSuccessListener {
                        findViewById<TextInputEditText>(R.id.nombre_autor).setText("")
                        findViewById<TextInputEditText>(R.id.fecha_nac).setText("")
                        findViewById<TextInputEditText>(R.id.pais_nac).setText("")
                        toast.setText("Autor Creado Exitosamente")
                        toast.show()
                    }
                    .addOnFailureListener { e ->
                        Log.w("Examen2B", "Error adding document", e)
                    }


            }


        }
    }
}