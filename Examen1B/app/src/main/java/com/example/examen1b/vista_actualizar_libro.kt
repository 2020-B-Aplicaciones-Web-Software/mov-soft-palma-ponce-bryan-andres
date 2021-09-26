package com.example.examen1b

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class vista_actualizar_libro : AdapterView.OnItemSelectedListener, AppCompatActivity() {
    var id_autor = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vista_actualizar_libro)
        val libro = intent.getParcelableExtra<Libro>("libro")
        val autor = intent.getParcelableExtra<Autor>("autor")
        //se asignan los valores actuales del libro
        if (libro != null) {
            findViewById<TextInputEditText>(R.id.act_titulo).setText(libro.titulo_libro)
            findViewById<TextInputEditText>(R.id.act_isbn).setText(libro.isbn)
            findViewById<EditText>(R.id.act_sinopsis).setText(libro.sinopsis)
            findViewById<TextInputEditText>(R.id.act_latitud).setText(libro.latitud.toString())
            findViewById<TextInputEditText>(R.id.act_longitud).setText(libro.longitud.toString())


        }
        //se obtienen los valores de los autores para el spinner
        val db = Firebase.firestore
        val collection = db.collection("autor")
        val arregloAutores = ArrayList<Autor>()
        collection.get()
            .addOnSuccessListener { documents ->

                for (document in documents) {
                    val nombre = document.data.get("nombre").toString()
                    val fecha = document.data.get("fecha").toString()
                    val pais = document.data.get("pais").toString()
                    arregloAutores.add(Autor(nombre, fecha, pais))
                }


                val spinner = findViewById<Spinner>(R.id.spinner_autores)
                val adapter = ArrayAdapter(
                    this,
                    android.R.layout.simple_spinner_item,
                    arregloAutores
                )
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner.adapter = adapter
                spinner.onItemSelectedListener = this
                arregloAutores.forEachIndexed { index, _ ->
                    if (libro != null) {
                        spinner.setSelection(index)
                    }
                }


            }

        val toast = Toast.makeText(applicationContext, "nombre", Toast.LENGTH_SHORT)

        //boton cancelar
        findViewById<Button>(R.id.btn_cancelar_act_libro).setOnClickListener {
            startActivity(
                Intent(
                    this,
                    vista_autor::class.java
                )
            )
        }


        val btn_act = findViewById<Button>(R.id.btn_act_libro)
        btn_act.setOnClickListener {
            val titulo = findViewById<TextInputEditText>(R.id.act_titulo).text.toString()
            val isbn = findViewById<TextInputEditText>(R.id.act_isbn).text.toString()
            val sinopsis = findViewById<EditText>(R.id.act_sinopsis).text.toString()
            val latitud =
                findViewById<TextInputEditText>(R.id.act_longitud).text.toString().toDouble()
            val longitud =
                findViewById<TextInputEditText>(R.id.act_latitud).text.toString().toDouble()

            val db = Firebase.firestore
            val refAutor = db.collection("autor")
                .whereEqualTo("nombre", autor?.nombre_autor)
                .whereEqualTo("pais", autor?.pais_nac)
                .whereEqualTo("fecha", autor?.fecha_nac)


            val arrayLibro = arrayListOf(titulo, isbn, sinopsis, latitud, longitud)
            if ((arrayLibro.any { it == "" })) {
                toast.setText("Por favor rellene los campos")
                toast.show()
            } else {
                refAutor
                    .get()
                    .addOnSuccessListener { documents ->
                        for (document in documents) {
                            val ref = db.collection("autor").document(document.id)
                            if (libro != null) {
                                ref.collection("libro")
                                    .whereEqualTo("titulo", libro.titulo_libro)
                                    .whereEqualTo("isbn", libro.isbn)
                                    .whereEqualTo("sinopsis", libro.sinopsis)
                                    .whereEqualTo("latitud", libro.latitud)
                                    .whereEqualTo("longitud", libro.longitud)
                                    .get()
                                    .addOnSuccessListener { libros ->
                                        for (libroReferenciado in libros) {
                                            val refLibro = ref.collection("libro")
                                                .document(libroReferenciado.id)
                                            refLibro.update("titulo", titulo)
                                            refLibro.update("isbn", isbn)
                                            refLibro.update("sinopsis", sinopsis)
                                            refLibro.update("latitud", latitud)
                                            refLibro.update("longitud", longitud)
                                        }

                                    }
                            }
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