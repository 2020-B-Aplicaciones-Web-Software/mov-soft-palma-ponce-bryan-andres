package com.example.practica1moviles

import android.os.Bundle
import android.view.ContextMenu
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class b_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)

        val arreglo = arrayListOf<Int>(1, 2, 3)
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arreglo
        )
        val listViewEjm = findViewById<ListView>(R.id.tvx_ejemplo)
        listViewEjm.adapter = adaptador
        val botonListView = findViewById<Button>(R.id.btn_ir_list_view_anadir)
        botonListView
            .setOnClickListener { anadirItemsAlListView(1, arreglo, adaptador) }

        /*listViewEjm
            .setOnItemLongClickListener { adapterView, view, posicion, id ->
                Log.i("list-view", "Dio Click ${posicion}")
                return@setOnItemLongClickListener true
            }*/
        registerForContextMenu(listViewEjm)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
    }


    fun anadirItemsAlListView(
        valor: Int,
        arreglo: ArrayList<Int>,
        adaptador: ArrayAdapter<Int>
    ) {
        arreglo.add(valor)
        adaptador.notifyDataSetChanged()
    }
}