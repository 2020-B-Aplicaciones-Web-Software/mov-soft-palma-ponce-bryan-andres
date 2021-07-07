import java.io.File
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class AutorArchivo {
    val path = "src/main/kotlin/autor.txt"
    fun crearAutor(autor: Autor): Boolean {
        val file = File(path)
        file.appendText(autor.toFile() + "\n")
        return true
    }

    fun bucarAutorPorID(id: Int?): Autor {
        var autor = Autor()
        val file = File(path)
        file.forEachLine {
            val datos = it.split(",")
            if (datos[0] == id.toString()) {
                autor = Autor(
                    datos[0].toInt(),
                    datos[1],
                    datos[2],
                    datos[3],
                    LocalDate.parse(datos[4], DateTimeFormatter.ISO_DATE)
                )
            }
        }
        return autor
    }


    fun verAutores(): ArrayList<Autor> {
        val listaAutores = ArrayList<Autor>()
        val file = File(path)
        file.forEachLine {
            val datos = it.split(",")
            listaAutores.add(
                Autor(
                    datos[0].toInt(),
                    datos[1],
                    datos[2],
                    datos[3],
                    LocalDate.parse(datos[4], DateTimeFormatter.ISO_DATE)

                )
            )
        }
        return listaAutores
    }

    fun actualizarAutor(id: Int, autor: Autor): Boolean {
        return try {
            val file = File(path)
            val autores = verAutores()
            file.writeText("")
            autores.forEach {
                if (it.getID() != id) {
                    file.appendText(it.toFile() + "\n")
                } else {
                    file.appendText(autor.toFile() + "\n")
                }
            }
            true
        } catch (e: Exception) {
            false
        }

    }

    fun eliminarAutor(id: Int): Boolean {
        return try {
            val file = File(path)
            val autores = verAutores()
            file.writeText("")
            autores.forEach {
                if (it.getID() != id) {
                    file.appendText(it.toFile() + "\n")
                }
            }
            true
        } catch (e: Exception) {
            false
        }

    }
}