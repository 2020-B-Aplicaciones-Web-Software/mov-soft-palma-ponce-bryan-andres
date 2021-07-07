import java.io.File

class LibroArchivo {
    val path = "src/main/kotlin/libro.txt"
    val gestorAutor = AutorArchivo()
    fun crearLibro(libro: Libro): Boolean {
        val file = File(path)
        file.appendText(libro.toFile() + "\n")
        return true
    }

    fun bucarLibroPorID(id: Int?): Libro {
        var libro = Libro()
        val file = File(path)
        file.forEachLine {
            val datos = it.split(",")
            if (datos[0] == id.toString())
                libro = Libro(
                    datos[0].toInt(),
                    datos[1],
                    gestorAutor.bucarAutorPorID(datos[2].toInt()),
                    datos[3].toDouble(),
                    datos[4].toInt(),
                    datos[5].toBoolean()

                )
        }
        return libro
    }

    fun bucarLibrosPorAutor(id: Int?): ArrayList<Libro> {
        val listaLibros = ArrayList<Libro>()
        val file = File(path)
        file.forEachLine {
            val datos = it.split(",")
            if (datos[2] == id.toString())
                listaLibros.add(
                    Libro(
                        datos[0].toInt(),
                        datos[1],
                        gestorAutor.bucarAutorPorID(datos[2].toInt()),
                        datos[3].toDouble(),
                        datos[4].toInt(),
                        datos[5].toBoolean()

                    )
                )
        }
        return listaLibros
    }

    fun verLibros(): ArrayList<Libro> {
        val listaLibros = ArrayList<Libro>()
        val file = File(path)
        file.forEachLine {
            val datos = it.split(",")
            listaLibros.add(
                Libro(
                    datos[0].toInt(),
                    datos[1],
                    gestorAutor.bucarAutorPorID(datos[2].toInt()),
                    datos[3].toDouble(),
                    datos[4].toInt(),
                    datos[5].toBoolean()

                )
            )
        }
        return listaLibros
    }

    fun actualizarLibro(id: Int, libro: Libro): Boolean {
        return try {
            val file = File(path)
            val libros = verLibros()
            file.writeText("")
            libros.forEach {
                if (it.getID() != id) {
                    file.appendText(it.toFile() + "\n")
                } else {
                    file.appendText(libro.toFile() + "\n")
                }
            }
            true
        } catch (e: Exception) {
            false
        }

    }

    fun eliminarLibro(id: Int): Boolean {
        return try {
            val file = File(path)
            val libroes = verLibros()
            file.writeText("")
            libroes.forEach {
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