package com.example.practiceapp

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
import java.util.*

class b_activity : AppCompatActivity() {
    var posicionSeleccionada = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)

        //Cracion del arreglo de numeros
        val arregloNumero = BDMemoria.arregloEntrenador

        //Creacion del adaptador
        val adaptador = ArrayAdapter(
            this, //contexto
            android.R.layout.simple_list_item_1, //Se define el Layout
            arregloNumero
        )

        //Se asigna el adaptador a la lista
        val listViewEjemplo = findViewById<ListView>(R.id.tvx_ejemplo)
        listViewEjemplo.adapter = adaptador

        //Se define la accion del boton
        val botonListView = findViewById<Button>(R.id.btn_ir_list_view_anadir)
        botonListView.setOnClickListener {
            anadirItemsAlListView(BEntrenador("Carolina", "c@c.com"), arregloNumero, adaptador)
        }

        //listViewEjemplo
        //     .setOnItemLongClickListener { adapterView, view, posicion, id ->
        //         Log.i("list-view", "Dio click ${posicion}")
        //         return@setOnItemLongClickListener true
        //}

        registerForContextMenu(listViewEjemplo)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)

        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        posicionSeleccionada = id
        Log.i("List-View",posicionSeleccionada.toString())

    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.editar -> {
                Log.i(
                    "List", "Editar ${
                    BDMemoria.arregloEntrenador[1
                    ]
                    }"
                )
                return true
            }
            R.id.eliminar -> {
                Log.i(
                    "List", "Editar ${
                    BDMemoria.arregloEntrenador[1
                    ]
                    }"
                )
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }


    fun anadirItemsAlListView(
        valor: BEntrenador,
        arreglo: ArrayList<BEntrenador>,
        adaptador: ArrayAdapter<BEntrenador>
    ) {
        arreglo.add(valor)
        adaptador.notifyDataSetChanged()
    }
}