import java.util.*

class Libro(
    private var id_libro: Int? = null,
    private var titulo_libro: String? = null,
    private var autor: Autor? = null,
    private var precio: Double? = null,
    private var num_paginas: Int? = null,
    private var disponible: Boolean? = null,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Libro

        if (id_libro != other.id_libro) return false
        if (titulo_libro != other.titulo_libro) return false
        if (autor != other.autor) return false
        if (precio != other.precio) return false
        if (num_paginas != other.num_paginas) return false
        if (disponible != other.disponible) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id_libro ?: 0
        result = 31 * result + (titulo_libro?.hashCode() ?: 0)
        result = 31 * result + (autor?.hashCode() ?: 0)
        result = 31 * result + (precio?.hashCode() ?: 0)
        result = 31 * result + (num_paginas ?: 0)
        result = 31 * result + (disponible?.hashCode() ?: 0)
        return result
    }
    fun getID(): Int? {
        return id_libro
    }

    override fun toString(): String {
        return "${id_libro}\t" +
                "${titulo_libro}\t\t" +
                "${autor?.getNombre() +" "+ autor?.getApellido()}\t\t" +
                "${precio}\t\t" +
                "${num_paginas}\t\t" +
                "${disponible}\n"
    }
     fun toFile(): String {
        return "${id_libro}," +
                "${titulo_libro}," +
                "${autor?.getID()}," +
                "${precio}," +
                "${num_paginas}," +
                "${disponible}\n"
    }

}