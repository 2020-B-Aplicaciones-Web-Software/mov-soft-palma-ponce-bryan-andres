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
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class lista_autores : AppCompatActivity() {

    var id_seleccionado = -1
    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 401
    var arrayAutores = ArrayList<Autor>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_autores)
        mostrarAutoresListView()
        val btn_anadir_autor = findViewById<FloatingActionButton>(
            R.id.btn_anadir_autor
        )
        btn_anadir_autor.setOnClickListener {
            cambiarActividad(vista_crear_autor::class.java)
        }


    }

    override fun onResume() {
        super.onResume()
        mostrarAutoresListView()
    }


    fun mostrarAutoresListView() {
        val db = Firebase.firestore
        val collection = db.collection("autor")

        val arregloAutores = ArrayList<Autor>()
        collection.get()
            .addOnSuccessListener { documents ->

                for (document in documents) {
                    val nombre = document.data.get("nombre").toString()
                    val fecha = document.data.get("fecha").toString()
                    val pais = document.data.get("pais").toString()
                    arregloAutores.add(Autor( nombre, fecha, pais))
                }
                arrayAutores = arregloAutores
                //Creacion del adaptador
                val adaptador = ArrayAdapter(
                    this, //contexto
                    android.R.layout.simple_list_item_1, //Se define el Layout
                    arrayAutores
                )
                //Se asigna el adaptador a la lista
                val listViewAutores = findViewById<ListView>(R.id.list_view_autores)
                registerForContextMenu(listViewAutores)
                listViewAutores.adapter = adaptador
            }

    }

    override fun onCreateContextMenu(

        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        // var posicionSeleccionada = 0
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_autores, menu)

        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        id_seleccionado = info.position
        //posicionSeleccionada = info.position

    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item_editar_autor -> {
                mostrarAutoresListView()
                irFormularioEditar()
                return true
            }
            R.id.item_eliminar_autor -> {
                val db = Firebase.firestore
                val referenciaAutor = db
                    .collection("autor")
                    .whereEqualTo("nombre", arrayAutores[id_seleccionado].nombre_autor)
                    .whereEqualTo("fecha", arrayAutores[id_seleccionado].fecha_nac)
                    .whereEqualTo("pais", arrayAutores[id_seleccionado].pais_nac)
                referenciaAutor
                    .get()
                    .addOnSuccessListener { result ->
                        for (document in result) {
                            val referencia_documento = db.collection("autor").document(document.id)
                            referencia_documento.delete()
                        }
                        onResume()
                    }
                onResume()
                return true
            }
            R.id.item_ver_autor -> {
                mostrarAutoresListView()
                irFormularioVistaAutor()
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun irFormularioEditar() {
        val editarAutorForm = Intent(
            this,
            vista_actualizar_autor::class.java
        )
        editarAutorForm.putExtra("autor", arrayAutores[id_seleccionado])
        startActivityForResult(editarAutorForm, CODIGO_RESPUESTA_INTENT_EXPLICITO)

    }

    fun irFormularioVistaAutor() {
        val editarAutorForm = Intent(
            this,
            vista_autor::class.java
        )
        editarAutorForm.putExtra("autor", arrayAutores[id_seleccionado])
        startActivityForResult(editarAutorForm, CODIGO_RESPUESTA_INTENT_EXPLICITO)

    }

    fun cambiarActividad(
        clase: Class<*>
    ) {
        val intentExplicito = Intent(
            this,
            clase
        )
        startActivity(intentExplicito)
    }

}