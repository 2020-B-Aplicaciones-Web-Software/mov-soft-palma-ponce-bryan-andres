package com.example.firebase2

class Detalle(
    var producto:Producto,
    var cantidad:Int
) {
    override fun toString(): String {
        return "${producto.nombre} - $cantidad - ${producto.precio*cantidad}"
    }
}