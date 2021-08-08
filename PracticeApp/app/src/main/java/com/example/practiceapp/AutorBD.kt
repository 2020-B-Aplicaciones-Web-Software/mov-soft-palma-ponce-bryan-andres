package com.example.practiceapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AutorBD(
    contexto: Context?
) : SQLiteOpenHelper(
    contexto,
    "moviles",
    null,
    1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptCrearTablaAutor =
            """
                CREATE TABLE AUTOR(
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nombre VARCHAR(50),
                    pais VARCHAR(50)
                    )
            """.trimIndent()
        db?.execSQL(scriptCrearTablaAutor)
    }

    fun crearAutorFormulario(
        nombre: String,
        pais: String
    ): Boolean {
        val conexionEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("nombre", nombre)
        valoresAGuardar.put("pais", pais)
        val resultadoEscritura: Long = conexionEscritura
            .insert(
                "AUTOR",
                null,
                valoresAGuardar
            )
        conexionEscritura.close()
        return resultadoEscritura.toInt() != -1
    }

    fun verAutores(): ArrayList<Autor> {
        val scripConsultarAutor = "SELECT * FROM AUTOR"
        val baseDeDatosLectura = readableDatabase
        val resultadoConsultaLectura = baseDeDatosLectura.rawQuery(
            scripConsultarAutor,
            null
        )
        val existeAutor = resultadoConsultaLectura.moveToFirst()
        val autorEncontrado = Autor(0, "", "")
        val arregloautores = arrayListOf<Autor>()
        if (existeAutor) {
            do {
                val id = resultadoConsultaLectura.getInt(0)
                val nombre = resultadoConsultaLectura.getString(1)
                val pais = resultadoConsultaLectura.getString(2)
                arregloautores.add(Autor(id, nombre, pais))
            } while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDeDatosLectura.close()
        return arregloautores
    }

    fun consultarAutorPorId(id: Int): Autor {
        val scripConsultarAutor = "SELECT * FROM AUTOR WHERE ID = ${id}"
        val baseDeDatosLectura = readableDatabase
        val resultadoConsultaLectura = baseDeDatosLectura.rawQuery(
            scripConsultarAutor,
            null
        )
        val existeAutor = resultadoConsultaLectura.moveToFirst()
        val autorEncontrado = Autor(0, "", "")
        val arregloautors = arrayListOf<Autor>()
        if (existeAutor) {
            do {

                val id = resultadoConsultaLectura.getInt(0)
                val nombre = resultadoConsultaLectura.getString(1)
                val pais = resultadoConsultaLectura.getString(2)
                autorEncontrado.autor_id = id
                autorEncontrado.nombre = nombre
                autorEncontrado.pais = pais
            } while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDeDatosLectura.close()
        return autorEncontrado

    }

    fun actualizarAutorFormulario(
        id: Int,
        nombre: String,
        pais: String
    ): Boolean {
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("nombre", nombre)
        valoresAActualizar.put("pais", pais)

        val resultaddoActualizacion = conexionEscritura
            .update(
                "autor",
                valoresAActualizar,
                "id=?",
                arrayOf(
                    id.toString()
                )
            )
        conexionEscritura.close()
        return resultaddoActualizacion != -1

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {


    }

    fun eliminarAutorFormulario(id: Int): Boolean {
        val conexionEscritura = writableDatabase
        val resultadoEliminacion = conexionEscritura
            .delete(
                "autor",
                "id=?",
                arrayOf(
                    id.toString()
                )
            )
        return resultadoEliminacion != -1
    }


}