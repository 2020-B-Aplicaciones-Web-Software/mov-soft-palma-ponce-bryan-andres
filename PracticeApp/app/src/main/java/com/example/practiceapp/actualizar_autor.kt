package com.example.practiceapp

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class actualizar_autor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_autor)
        val autor = intent.getParcelableExtra<Autor>("autor")
        if (autor != null) {
            findViewById<TextInputEditText>(R.id.texto_nombre).setText(autor.nombre)
            findViewById<TextInputEditText>(R.id.texto_pais).setText(autor.pais)
        }
        val btn_anadir_autores = findViewById<Button>(
            R.id.boton_actualizar
        )
        btn_anadir_autores.setOnClickListener {
            val nombre = findViewById<TextInputEditText>(R.id.texto_nombre).text
            val pais = findViewById<TextInputEditText>(R.id.texto_pais).text
            val toast = Toast.makeText(applicationContext, nombre, Toast.LENGTH_SHORT)
            if ((pais.toString() == "") or (nombre.toString() == "")) {
                toast.setText("Por favor rellene los campos")
                toast.show()
            } else {
                val autorBD = AutorBD(this)
                if (autor != null) {
                    if (autorBD.actualizarAutorFormulario(
                            autor.autor_id,
                            nombre.toString(),
                            pais.toString()
                        )
                    ) {
                        findViewById<TextInputEditText>(R.id.texto_nombre).setText("")
                        findViewById<TextInputEditText>(R.id.texto_pais).setText("")
                        toast.setText("Autor Actualizado Exitosamente")
                        toast.show()
                    }
                }
            }


        }

    }
}