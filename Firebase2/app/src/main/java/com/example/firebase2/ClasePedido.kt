package com.example.firebase2

class ClasePedido(
    var restaurante: Restaurante,
    var detalle:ArrayList<Detalle>,
    var total: Double,
) {
    override fun toString(): String {
        return "ClasePedido(restaurante=$restaurante, detalle=$detalle, total=$total)"
    }
}