package com.example.examen1b

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class vista_actualizar_libro : AdapterView.OnItemSelectedListener, AppCompatActivity() {
    var id_autor = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vista_actualizar_libro)
        val libro = intent.getParcelableExtra<Libro>("libro")

        if (libro != null) {
            findViewById<TextInputEditText>(R.id.act_titulo).setText(libro.titulo_libro)
            findViewById<TextInputEditText>(R.id.act_isbn).setText(libro.isbn)
            findViewById<EditText>(R.id.act_sinopsis).setText(libro.sinopsis)
            id_autor = libro.autor?.autor_id!!
        }

        val autorBD = AutorBD(this)
        val libroBD = LibroBD(this)

        val autores = autorBD.verAutores()

        val toast = Toast.makeText(applicationContext, "nombre", Toast.LENGTH_SHORT)

        findViewById<Button>(R.id.btn_cancelar_act_libro).setOnClickListener {
            startActivity(
                Intent(
                    this,
                    vista_autor::class.java
                )
            )
        }

        val spinner = findViewById<Spinner>(R.id.spinner_autores)
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            autores
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = this


        autores.forEachIndexed { index, autor ->
            if (libro != null) {
                if (autor.autor_id == id_autor) {
                    spinner.setSelection(index)
                }
            }
        }


        val btn_act = findViewById<Button>(R.id.btn_act_libro)
        btn_act.setOnClickListener {
            val titulo = findViewById<TextInputEditText>(R.id.act_titulo).text.toString()
            val isbn = findViewById<TextInputEditText>(R.id.act_isbn).text.toString()
            val sinopsis = findViewById<EditText>(R.id.act_sinopsis).text.toString()

            val arrayLibro = arrayListOf(titulo, isbn, sinopsis)
            if ((arrayLibro.any { it == "" })) {
                toast.setText("Por favor rellene los campos")
                toast.show()
            } else {
                if (libro != null) {
                    if (libroBD.actualizarLibroFormulario(
                            libro.id_libro,
                            titulo,
                            isbn,
                            sinopsis,
                            (spinner.selectedItem as Autor).autor_id
                        )
                    ) {
                        toast.setText("Libro Actualizado Exitosamente")
                        toast.show()
                    }


                }
            }
        }

    }


    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        id_autor = position

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }


}