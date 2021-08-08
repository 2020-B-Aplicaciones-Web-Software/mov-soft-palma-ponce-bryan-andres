package com.example.practiceapp

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class formulario_autor : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_autor)
        val btn_anadir_autores = findViewById<Button>(
            R.id.boton_anadir
        )
        btn_anadir_autores.setOnClickListener {
            val nombre = findViewById<TextInputEditText>(R.id.texto_nombre).text
            val pais = findViewById<TextInputEditText>(R.id.texto_pais).text
            val toast = Toast.makeText(applicationContext, nombre, Toast.LENGTH_SHORT)
            if ((pais.toString() == "") or (nombre.toString() == "")) {
                toast.setText("Por favor rellene los campos")
                toast.show()
            } else {
                val autor = AutorBD(this)
                if (autor.crearAutorFormulario(nombre.toString(), pais.toString())) {
                    findViewById<TextInputEditText>(R.id.texto_nombre).setText("")
                    findViewById<TextInputEditText>(R.id.texto_pais).setText("")
                    toast.setText("Autor Creado Exitosamente")
                    toast.show()
                }
            }


        }


    }
}