package com.example.examen1b

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class vista_autor : AppCompatActivity() {
    val libroBD = LibroBD(this)
    var id_seleccionado = -1
    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 401
    lateinit var globalautor:Autor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vista_autor)


        val autor = intent.getParcelableExtra<Autor>("autor")
        if (autor != null) {
            findViewById<TextView>(R.id.label_nombre_autor).text = autor.nombre_autor
            findViewById<TextView>(R.id.fecha_nac).text = autor.fecha_nac
            findViewById<TextView>(R.id.lugar_nac).text = autor.pais_nac
            mostrarLibrosListView(autor.autor_id)
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
            crearLibroForm.putExtra("autor",autor)
            startActivityForResult(crearLibroForm, CODIGO_RESPUESTA_INTENT_EXPLICITO)
        }

    }

    override fun onResume() {
        super.onResume()
        mostrarLibrosListView(globalautor.autor_id)

    }

    override fun onRestart() {
        super.onRestart()
        mostrarLibrosListView(globalautor.autor_id)
    }

    fun mostrarLibrosListView(id_autor: Int) {
        var arregloLibros = libroBD.buscarLibrosPorAutor(id_autor)
        //Creacion del adaptador
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
        var arregloLibros = libroBD.verLibros()
        return when (item.itemId) {
            R.id.item_editar_libro -> {
                irFormularioEditar()
                return true
            }
            R.id.item_eliminar_libro -> {
                libroBD.eliminarLibroFormulario(arregloLibros[id_seleccionado].id_libro)
                onResume()
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun irFormularioEditar() {
        var arregloLibros = libroBD.verLibros()
        val editarLibroForm = Intent(
            this,
            vista_actualizar_libro::class.java
        )
        editarLibroForm.putExtra("libro", arregloLibros[id_seleccionado])
        startActivityForResult(editarLibroForm, CODIGO_RESPUESTA_INTENT_EXPLICITO)

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