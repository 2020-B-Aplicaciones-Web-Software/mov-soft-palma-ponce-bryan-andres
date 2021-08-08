package com.example.examen1b

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class vista_actualizar_autor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vista_actualizar_autor)

        findViewById<Button>(R.id.btn_cancelar_act).setOnClickListener {
            startActivity(
                Intent(
                    this,
                    lista_autores::class.java
                )
            )
        }

        val toast = Toast.makeText(applicationContext, "nombre", Toast.LENGTH_SHORT)
        var autor = intent.getParcelableExtra<Autor>("autor")
        if (autor != null) {
            findViewById<TextInputEditText>(R.id.nombre_autor_actualizar).setText(autor.nombre_autor)
            findViewById<TextInputEditText>(R.id.fecha_autor_a).setText(autor.fecha_nac)
            findViewById<TextInputEditText>(R.id.pais_autor_actualizar).setText(autor.pais_nac)
        }
        val btn_act = findViewById<Button>(R.id.btn_actualizar)
        btn_act.setOnClickListener {
            var autorBD = AutorBD(this)
            val nombre =
                findViewById<TextInputEditText>(R.id.nombre_autor_actualizar).text.toString()
            val pais = findViewById<TextInputEditText>(R.id.pais_autor_actualizar).text.toString()
            val fecha = findViewById<TextInputEditText>(R.id.fecha_autor_a).text.toString()
            val arrayAutor = arrayListOf(nombre, pais, fecha)
            if ((arrayAutor.any { it == "" })) {
                toast.setText("Por favor rellene los campos")
                toast.show()
            } else {
                if (autor != null) {
                    autorBD.actualizarAutorFormulario(autor.autor_id, nombre, pais, fecha)
                    toast.setText("Autor Actualizado Exitosamente")
                    toast.show()
                }
            }
        }
    }
}