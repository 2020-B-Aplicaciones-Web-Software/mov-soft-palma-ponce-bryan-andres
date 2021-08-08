package com.example.examen1b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.widget.AppCompatEditText
import com.google.android.material.textfield.TextInputEditText

class vista_crear_libro :AppCompatActivity() {
    val autorBD=AutorBD(this)
    var id_autor=-1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vista_crear_libro)

        findViewById<Button>(R.id.btn_cancelar_crear).setOnClickListener {
            startActivity(
                Intent(
                    this,
                    vista_autor::class.java
                )
            )
        }

        var autor = intent.getParcelableExtra<Autor>("autor")
        if (autor != null) {
            findViewById<TextView>(R.id.fixed_autor).text=autor.toString()
            id_autor=autor.autor_id
        }
        val btn_anadir_libro = findViewById<Button>(
            R.id.btn_crear
        )
        btn_anadir_libro.setOnClickListener {
            val titulo = findViewById<TextInputEditText>(R.id.titulo_libro).text.toString()
            val isbn = findViewById<TextInputEditText>(R.id.isbn).text.toString()
            val sinopsis = findViewById<AppCompatEditText>(R.id.sinopsis).text.toString()
            val toast = Toast.makeText(applicationContext, titulo, Toast.LENGTH_SHORT)
            val arrayAutor= arrayListOf(titulo,isbn,sinopsis)
            if ((arrayAutor.any{it==""})) {
                toast.setText("Por favor rellene los campos")
                toast.show()
            } else {
                val autor = LibroBD(this)
                if (autor.crearLibroFormulario(titulo,isbn,sinopsis,id_autor)) {
                    findViewById<TextInputEditText>(R.id.titulo_libro).setText("")
                    findViewById<TextInputEditText>(R.id.isbn).setText("")
                    findViewById<AppCompatEditText>(R.id.sinopsis).setText("")
                    toast.setText("Libro Creado Exitosamente")
                    toast.show()
                }
            }


        }

    }



}