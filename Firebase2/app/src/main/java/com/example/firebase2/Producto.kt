package com.example.firebase2

class Producto(
    var nombre: String,
    var precio: Double,
) {
    override fun toString(): String {
        return "$nombre-$precio"
    }
}