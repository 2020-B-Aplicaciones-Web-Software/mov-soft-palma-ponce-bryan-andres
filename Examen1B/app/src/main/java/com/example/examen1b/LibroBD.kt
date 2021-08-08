package com.example.examen1b

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class LibroBD(
    var contexto: Context?
) : SQLiteOpenHelper(
    contexto,
    "moviles",
    null,
    1
) {
    var autorBD = AutorBD(this.contexto)
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptCrearTablaLibro =
            """
                CREATE TABLE LIBRO(
                    id_libro INTEGER PRIMARY KEY AUTOINCREMENT,
                    titulo VARCHAR(50),
                    isbn VARCHAR(20),
                    sinopsis VARCHAR(255),
                    autor INTEGER,
                    FOREIGN KEY (autor) REFERENCES AUTOR (id_autor) 
                    )
            """.trimIndent()
        db?.execSQL(scriptCrearTablaLibro)
    }

    fun crearLibroFormulario(
        titulo: String,
        isbn: String,
        sinopsis: String,
        autor: Int
    ): Boolean {
        val conexionEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("titulo", titulo)
        valoresAGuardar.put("isbn", isbn)
        valoresAGuardar.put("sinopsis", sinopsis)
        valoresAGuardar.put("autor", autor)
        val resultadoEscritura: Long = conexionEscritura
            .insert(
                "LIBRO",
                null,
                valoresAGuardar
            )
        conexionEscritura.close()
        return resultadoEscritura.toInt() != -1
    }

    fun verLibros(): ArrayList<Libro> {
        val scripConsultarLibro = "SELECT * FROM LIBRO"
        val baseDeDatosLectura = readableDatabase
        val resultadoConsultaLectura = baseDeDatosLectura.rawQuery(
            scripConsultarLibro,
            null
        )
        val existeLibro = resultadoConsultaLectura.moveToFirst()
        val arreglolibros = arrayListOf<Libro>()
        if (existeLibro) {
            do {
                val id = resultadoConsultaLectura.getInt(0)
                val titulo = resultadoConsultaLectura.getString(1)
                val isbn = resultadoConsultaLectura.getString(2)
                val sinopsis = resultadoConsultaLectura.getString(3)
                val autor = autorBD.consultarAutorPorId(resultadoConsultaLectura.getInt(4))
                arreglolibros.add(Libro(id, titulo, isbn, sinopsis, autor))
            } while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDeDatosLectura.close()
        return arreglolibros
    }

    fun consultarLibroPorId(id: Int): Libro {
        val scripConsultarLibro = "SELECT * FROM LIBRO WHERE ID_LIBRO = ${id}"
        val baseDeDatosLectura = readableDatabase
        val resultadoConsultaLectura = baseDeDatosLectura.rawQuery(
            scripConsultarLibro,
            null
        )
        val existeLibro = resultadoConsultaLectura.moveToFirst()
        val autorEncontrado = Libro(0, "", "", "", Autor(0, "", "", ""))
        if (existeLibro) {
            do {

                val id = resultadoConsultaLectura.getInt(0)
                val titulo = resultadoConsultaLectura.getString(1)
                val isbn = resultadoConsultaLectura.getString(2)
                val sinopsis = resultadoConsultaLectura.getString(3)
                val autor = resultadoConsultaLectura.getInt(4)
                autorEncontrado.id_libro = id
                autorEncontrado.titulo_libro = titulo
                autorEncontrado.isbn = isbn
                autorEncontrado.sinopsis = sinopsis
                autorEncontrado.autor = autorBD.consultarAutorPorId(autor)
            } while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDeDatosLectura.close()
        return autorEncontrado

    }

    fun actualizarLibroFormulario(
        id: Int,
        titulo: String,
        isbn: String,
        sinopsis: String,
        autor: Int
    ): Boolean {
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("titulo", titulo)
        valoresAActualizar.put("isbn", isbn)
        valoresAActualizar.put("sinopsis", sinopsis)
        valoresAActualizar.put("autor", autor)

        val resultaddoActualizacion = conexionEscritura
            .update(
                "LIBRO",
                valoresAActualizar,
                "id_libro=?",
                arrayOf(
                    id.toString()
                )
            )
        conexionEscritura.close()
        return resultaddoActualizacion != -1

    }

    fun eliminarLibroFormulario(id: Int): Boolean {
        val conexionEscritura = writableDatabase
        val resultadoEliminacion = conexionEscritura
            .delete(
                "LIBRO",
                "id_libro=?",
                arrayOf(
                    id.toString()
                )
            )
        return resultadoEliminacion != -1
    }

    fun buscarLibrosPorAutor(id_autor: Int): ArrayList<Libro> {
        val scripConsultarLibro = "SELECT * FROM LIBRO WHERE AUTOR = ${id_autor}"
        val baseDeDatosLectura = readableDatabase
        val resultadoConsultaLectura = baseDeDatosLectura.rawQuery(
            scripConsultarLibro,
            null
        )
        val existeLibro = resultadoConsultaLectura.moveToFirst()
        val arreglolibros = arrayListOf<Libro>()
        if (existeLibro) {
            do {
                val id = resultadoConsultaLectura.getInt(0)
                val titulo = resultadoConsultaLectura.getString(1)
                val isbn = resultadoConsultaLectura.getString(2)
                val sinopsis = resultadoConsultaLectura.getString(3)
                val autor = autorBD.consultarAutorPorId(resultadoConsultaLectura.getInt(4))
                arreglolibros.add(Libro(id, titulo, isbn, sinopsis, autor))
            } while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDeDatosLectura.close()
        return arreglolibros
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}
