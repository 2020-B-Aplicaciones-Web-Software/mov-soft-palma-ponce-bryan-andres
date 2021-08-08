package com.example.practiceapp.examen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.practiceapp.*

class vista_autor : AppCompatActivity() {
    var autorBD = AutorBD(this)
    var arregloAutores1 = ArrayList<Autor>()
    var id_seleccionado = -1
    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 401
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vista_autor)

        mostrarAutoresListView()
        val btn_anadir_autores = findViewById<Button>(
            R.id.btn_anadir_autor
        )
        btn_anadir_autores.setOnClickListener {
            val intentExplicito = Intent(
                this,
                formulario_autor::class.java
            )
            startActivity(intentExplicito)
        }
    }

    override fun onResume() {
        super.onResume()
        mostrarAutoresListView()
    }


    fun mostrarAutoresListView() {
        val arregloAutores = autorBD.verAutores()
        arregloAutores1 = arregloAutores
        //Creacion del adaptador
        val adaptador = ArrayAdapter(
            this, //contexto
            android.R.layout.simple_list_item_1, //Se define el Layout
            arregloAutores
        )
        //Se asigna el adaptador a la lista
        val listViewAutores = findViewById<ListView>(R.id.lista_autores)
        registerForContextMenu(listViewAutores)
        listViewAutores.adapter = adaptador


    }

    override fun onCreateContextMenu(

        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        // var posicionSeleccionada = 0
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)

        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        id_seleccionado = info.position
        //posicionSeleccionada = info.position


    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.editar -> {
                irFormularioEditar()
                return true
            }
            R.id.eliminar -> {
                autorBD.eliminarAutorFormulario(arregloAutores1[id_seleccionado].autor_id)
                onResume()
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun irFormularioEditar() {
        val editarAutorForm = Intent(
            this,
            actualizar_autor::class.java
        )
        editarAutorForm.putExtra("autor", arregloAutores1[id_seleccionado])
        startActivityForResult(editarAutorForm, CODIGO_RESPUESTA_INTENT_EXPLICITO)

    }

    fun printLog(texto: String) {
        Log.i("BDD", texto)
    }
}