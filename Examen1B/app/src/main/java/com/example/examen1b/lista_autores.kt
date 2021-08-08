package com.example.examen1b

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class lista_autores : AppCompatActivity() {

    var autorBD = AutorBD(this)
    var id_seleccionado = -1
    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 401

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
        var arregloAutores = autorBD.verAutores()
        //Creacion del adaptador
        val adaptador = ArrayAdapter(
            this, //contexto
            android.R.layout.simple_list_item_1, //Se define el Layout
            arregloAutores
        )
        //Se asigna el adaptador a la lista
        val listViewAutores = findViewById<ListView>(R.id.list_view_autores)
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
        inflater.inflate(R.menu.menu_autores, menu)

        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        id_seleccionado = info.position
        //posicionSeleccionada = info.position


    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        var arregloAutores = autorBD.verAutores()
        return when (item.itemId) {
            R.id.item_editar_autor -> {
                irFormularioEditar()
                return true
            }
            R.id.item_eliminar_autor -> {
                autorBD.eliminarAutorFormulario(arregloAutores[id_seleccionado].autor_id)
                onResume()
                return true
            }
            R.id.item_ver_autor -> {
                irFormularioVistaAutor()
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun irFormularioEditar() {
        var arregloAutores = autorBD.verAutores()
        val editarAutorForm = Intent(
            this,
            vista_actualizar_autor::class.java
        )
        editarAutorForm.putExtra("autor", arregloAutores[id_seleccionado])
        startActivityForResult(editarAutorForm, CODIGO_RESPUESTA_INTENT_EXPLICITO)

    }

    fun irFormularioVistaAutor() {
        var arregloAutores = autorBD.verAutores()
        val editarAutorForm = Intent(
            this,
            vista_autor::class.java
        )
       editarAutorForm.putExtra("autor", arregloAutores[id_seleccionado])
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