package com.example.examen1b

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class vista_crear_libro : AppCompatActivity() {
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
            findViewById<TextView>(R.id.fixed_autor).text = autor.toString()

        }
        val btn_anadir_libro = findViewById<Button>(
            R.id.btn_crear
        )
        btn_anadir_libro.setOnClickListener {
            val titulo = findViewById<TextInputEditText>(R.id.titulo_libro).text.toString()
            val isbn = findViewById<TextInputEditText>(R.id.isbn).text.toString()
            val sinopsis = findViewById<AppCompatEditText>(R.id.sinopsis).text.toString()
            val latitud = findViewById<AppCompatEditText>(R.id.latitud_libro).text.toString()
            val longitud = findViewById<AppCompatEditText>(R.id.longitud_libro).text.toString()
            val toast = Toast.makeText(applicationContext, titulo, Toast.LENGTH_SHORT)
            val arrayAutor = arrayListOf(titulo, isbn, sinopsis, latitud, longitud)
            if ((arrayAutor.any { it == "" })) {
                toast.setText("Por favor rellene los campos")
                toast.show()
            } else {
                val nuevoLibro = hashMapOf<String, Any>(
                    "titulo" to titulo,
                    "isbn" to isbn,
                    "sinopsis" to sinopsis,
                    "latitud" to latitud.toDouble(),
                    "longitud" to longitud.toDouble()
                )
                val db = Firebase.firestore
                val refAutor = db.collection("autor")
                    .whereEqualTo("nombre", autor?.nombre_autor)
                    .whereEqualTo("fecha", autor?.fecha_nac)
                    .whereEqualTo("pais", autor?.pais_nac)
                refAutor
                    .get()
                    .addOnSuccessListener { documents ->
                        for (document in documents) {
                            val refLibro = db.collection("autor").document(document.id)
                            refLibro.collection("libro")
                                .add(nuevoLibro)
                            findViewById<TextInputEditText>(R.id.titulo_libro).setText("")
                            findViewById<TextInputEditText>(R.id.isbn).setText("")
                            findViewById<AppCompatEditText>(R.id.sinopsis).setText("")
                            findViewById<TextInputEditText>(R.id.latitud_libro).setText("")
                            findViewById<TextInputEditText>(R.id.longitud_libro).setText("")
                            toast.setText("Libro Creado Exitosamente")
                            toast.show()
                        }
                    }
            }

        }

    }


}