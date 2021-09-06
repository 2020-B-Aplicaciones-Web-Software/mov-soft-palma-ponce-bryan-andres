package com.example.firebase2

import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class Pedido : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    var id_seleccionado = -1
    var detalles = ArrayList<Detalle>()
    var total = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        val db = Firebase.firestore
        val referenciaRestaurantes = db.collection("restaurante")
        val restaurantes = ArrayList<Restaurante>()
        referenciaRestaurantes.get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val restaurante = Restaurante(document["nombre"] as String)
                    restaurantes.add(restaurante)
                }
                fillSpinnerRestaurante(restaurantes)
            }
            .addOnFailureListener { exception ->
                Log.d("pedido", "Error getting documents: ", exception)
            }

        val referenciaProductos = db.collection("producto")
        val productos = ArrayList<Producto>()
        referenciaProductos.get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val producto = Producto(
                        document.data["nombre"] as String,
                        document.data["precio"] as Double
                    )
                    productos.add(producto)
                }
                fillSpinnerProductos(productos)
            }
            .addOnFailureListener { exception ->
                Log.d("pedido", "Error getting documents: ", exception)
            }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pedido)
        val spinner = findViewById<Spinner>(R.id.sp_producto)
        val cantidad = findViewById<EditText>(R.id.et_cantidad_producto)
        val btn_anadir = findViewById<Button>(R.id.btn_anadir_lista_producto)
        val btn_completar = findViewById<Button>(R.id.btn_completar_pedido)
        btn_completar.setOnClickListener {
            completarPedido()
        }
        btn_anadir.setOnClickListener {

            try {
                anadirDetalle(
                    Detalle(
                        spinner.selectedItem as Producto,
                        cantidad.text.toString().toInt()
                    )
                )
            } catch (e: Exception) {
                anadirDetalle(Detalle(spinner.selectedItem as Producto, 1))
            }

        }

    }

    private fun completarPedido() {
        val restaurante = findViewById<Spinner>(R.id.sp_restaurantes).selectedItem as Restaurante
        val pedido = ClasePedido(restaurante, detalles, total)
        Log.i("spinner", "$pedido")
    }

    fun actualizarListaProductos() {
        val adaptador = ArrayAdapter(
            this, //contexto
            android.R.layout.simple_list_item_1, //Se define el Layout
            detalles
        )
        //Se asigna el adaptador a la lista
        val listViewProducto = findViewById<ListView>(R.id.lv_lista_productos)
        registerForContextMenu(listViewProducto)
        listViewProducto.adapter = adaptador
        total = 0.0
        detalles.forEach { total += it.cantidad * it.producto.precio }
        findViewById<TextView>(R.id.tv_total).text = "Total $total"
    }

    private fun anadirDetalle(detalle: Detalle) {
        var hasDetalle = false
        detalles.forEach {
            if (detalle.producto == it.producto) {
                it.cantidad += detalle.cantidad
                hasDetalle = true
            }
        }
        if (!hasDetalle) {
            detalles.add(detalle)
        }
        actualizarListaProductos()
    }


    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        // var posicionSeleccionada = 0
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_productos, menu)

        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        id_seleccionado = info.position
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.btn_eliminar -> {
                detalles.removeAt(id_seleccionado)
                actualizarListaProductos()
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    private fun fillSpinnerRestaurante(arregloRestautantes: ArrayList<Restaurante>) {
        val spinner = findViewById<Spinner>(R.id.sp_restaurantes)
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            arregloRestautantes
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = this
    }

    private fun fillSpinnerProductos(arregloProductos: ArrayList<Producto>) {
        val spinner = findViewById<Spinner>(R.id.sp_producto)
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            arregloProductos as ArrayList<*>
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = this

    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}