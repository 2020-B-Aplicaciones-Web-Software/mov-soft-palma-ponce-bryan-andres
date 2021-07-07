import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

class Autor(
    private var id_autor: Int? = null,
    private var nombre_autor: String? = null,
    private var apellido_autor: String? = null,
    private var pais_autor: String? = null,
    private var fecha_autor: LocalDate? = null,
) {

    override fun toString(): String {
        return "${id_autor}\t" +
                "${nombre_autor}\t\t" +
                "${apellido_autor}\t\t" +
                "${pais_autor}\t\t" +
                String.format("%02d", fecha_autor?.dayOfMonth) +
                "/${String.format("%02d", fecha_autor?.monthValue)}" +
                "/${fecha_autor?.year}\n"
    }
    fun toFile():String{
        return "${id_autor}," +
                "${nombre_autor}," +
                "${apellido_autor}," +
                "${pais_autor}," +
                "${fecha_autor?.year}-" +
                "${String.format("%02d", fecha_autor?.monthValue)}-" +
                String.format("%02d", fecha_autor?.dayOfMonth)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Autor

        if (id_autor != other.id_autor) return false
        if (nombre_autor != other.nombre_autor) return false
        if (apellido_autor != other.apellido_autor) return false
        if (pais_autor != other.pais_autor) return false
        if (fecha_autor != other.fecha_autor) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id_autor ?: 0
        result = 31 * result + (nombre_autor?.hashCode() ?: 0)
        result = 31 * result + (apellido_autor?.hashCode() ?: 0)
        result = 31 * result + (pais_autor?.hashCode() ?: 0)
        result = 31 * result + (fecha_autor?.hashCode() ?: 0)
        return result
    }

    fun getID(): Int? {
        return id_autor
    }
    fun getNombre(): String? {
        return nombre_autor
    }
    fun getApellido(): String? {
        return apellido_autor
    }
}
