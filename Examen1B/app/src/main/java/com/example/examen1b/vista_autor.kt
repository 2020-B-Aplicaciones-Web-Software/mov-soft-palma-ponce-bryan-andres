package com.example.examen1b

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class vista_autor : AppCompatActivity() {
    var id_seleccionado = -1
    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 401
    var arrayLibros = ArrayList<Libro>()
    lateinit var globalautor: Autor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vista_autor)


        val autor = intent.getParcelableExtra<Autor>("autor")
        if (autor != null) {
            findViewById<TextView>(R.id.label_nombre_autor).text = autor.nombre_autor
            findViewById<TextView>(R.id.fecha_nac).text = autor.fecha_nac
            findViewById<TextView>(R.id.lugar_nac).text = autor.pais_nac
            mostrarLibrosListView(autor)
            globalautor = autor
        }

        val btn_anadir_libro = findViewById<FloatingActionButton>(
            R.id.btn_anadir_libro
        )
        btn_anadir_libro.setOnClickListener {
            val crearLibroForm = Intent(
                this,
                vista_crear_libro::class.java
            )
            crearLibroForm.putExtra("autor", autor)
            startActivityForResult(crearLibroForm, CODIGO_RESPUESTA_INTENT_EXPLICITO)
        }

    }

    override fun onResume() {
        super.onResume()
        mostrarLibrosListView(globalautor)

    }


    fun mostrarLibrosListView(autor: Autor) {
        val db = Firebase.firestore
        val arregloLibros = ArrayList<Libro>()
        val refAutor = db.collection("autor")
            .whereEqualTo("nombre", autor.nombre_autor)
            .whereEqualTo("fecha", autor.fecha_nac)
            .whereEqualTo("pais", autor.pais_nac)
        refAutor.get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val refLibro = db.collection("autor").document(document.id)
                        .collection("libro")
                    refLibro.get()
                        .addOnSuccessListener { libros ->
                            for (libro in libros) {
                                val titulo = libro.data.get("titulo").toString()
                                val isbn = libro.data.get("isbn").toString()
                                val sinopsis = libro.data.get("sinopsis").toString()
                                val latitud = libro.data.get("latitud").toString().toDouble()
                                val longitud = libro.data.get("longitud").toString().toDouble()
                                arregloLibros.add(Libro(titulo, isbn, sinopsis, latitud, longitud))
                            }
                            arrayLibros = arregloLibros

                            val adaptador = ArrayAdapter(
                                this, //contexto
                                android.R.layout.simple_list_item_1, //Se define el Layout
                                arregloLibros
                            )
                            //Se asigna el adaptador a la lista
                            val listViewLibros = findViewById<ListView>(R.id.lista_libros)
                            registerForContextMenu(listViewLibros)
                            listViewLibros.adapter = adaptador
                        }
                }

            }


    }

    override fun onCreateContextMenu(

        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_libros, menu)

        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        id_seleccionado = info.position


    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        onResume()
        mostrarLibrosListView(globalautor)
        return when (item.itemId) {
            R.id.item_editar_libro -> {
                irFormularioEditar()
                return true
            }
            R.id.item_eliminar_libro -> {
                val db = Firebase.firestore
                val refAutor = db.collection("autor")
                    .whereEqualTo("nombre", globalautor.nombre_autor)
                    .whereEqualTo("pais", globalautor.pais_nac)
                    .whereEqualTo("fecha", globalautor.fecha_nac)

                refAutor
                    .get()
                    .addOnSuccessListener { documents ->
                        for (document in documents) {
                            val ref = db.collection("autor").document(document.id)
                            ref.collection("libro")
                                .whereEqualTo("titulo", arrayLibros[id_seleccionado].titulo_libro)
                                .whereEqualTo("isbn", arrayLibros[id_seleccionado].isbn)
                                .whereEqualTo("sinopsis", arrayLibros[id_seleccionado].sinopsis)
                                .whereEqualTo("latitud", arrayLibros[id_seleccionado].latitud)
                                .whereEqualTo("longitud", arrayLibros[id_seleccionado].longitud)
                                .get()
                                .addOnSuccessListener { libros ->
                                    for (libroReferenciado in libros) {
                                        val refLibro = ref.collection("libro")
                                            .document(libroReferenciado.id)
                                        refLibro.delete()
                                        onResume()
                                    }

                                }
                        }
                    }

                return true
            }
            R.id.btn_ubicacion -> {
                irUbicacion()
                return true

            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun irFormularioEditar() {

        val editarLibroForm = Intent(
            this,
            vista_actualizar_libro::class.java
        )
        editarLibroForm.putExtra("libro", arrayLibros[id_seleccionado])
        editarLibroForm.putExtra("autor", globalautor)
        startActivityForResult(editarLibroForm, CODIGO_RESPUESTA_INTENT_EXPLICITO)

    }

    fun irUbicacion(
    ) {
        val intentExplicito = Intent(
            this,
            ubicacion_libro::class.java
        )
        intentExplicito.putExtra("libro", arrayLibros[id_seleccionado])
        intentExplicito.putExtra("autor", globalautor)
        startActivityForResult(intentExplicito, CODIGO_RESPUESTA_INTENT_EXPLICITO)
    }


}