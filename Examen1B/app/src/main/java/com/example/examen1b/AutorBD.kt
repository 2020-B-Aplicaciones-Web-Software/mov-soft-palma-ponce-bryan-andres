package com.example.examen1b

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AutorBD(
    var contexto: Context?
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
                    id_autor INTEGER PRIMARY KEY AUTOINCREMENT,
                    nombre VARCHAR(50),
                    fecha VARCHAR(20),
                    pais VARCHAR(50)
                    )
            """.trimIndent()
        db?.execSQL(scriptCrearTablaAutor)
    }

    fun crearAutorFormulario(
        nombre: String,
        fecha: String,
        pais: String
    ): Boolean {
        val conexionEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("nombre", nombre)
        valoresAGuardar.put("fecha", fecha)
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
    companion object{

    }

    fun verAutores(): ArrayList<Autor> {
        val scripConsultarAutor = "SELECT * FROM AUTOR"
        val baseDeDatosLectura = readableDatabase
        val resultadoConsultaLectura = baseDeDatosLectura.rawQuery(
            scripConsultarAutor,
            null
        )
        val existeAutor = resultadoConsultaLectura.moveToFirst()
        val arregloautores = arrayListOf<Autor>()
        if (existeAutor) {
            do {
                val id = resultadoConsultaLectura.getInt(0)
                val nombre = resultadoConsultaLectura.getString(1)
                val fecha = resultadoConsultaLectura.getString(2)
                val pais = resultadoConsultaLectura.getString(3)
                arregloautores.add(Autor(id, nombre, fecha, pais))
            } while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDeDatosLectura.close()
        return arregloautores
    }

    fun consultarAutorPorId(id: Int): Autor {
        val scripConsultarAutor = "SELECT * FROM AUTOR WHERE ID_AUTOR = ${id}"
        val baseDeDatosLectura = readableDatabase
        val resultadoConsultaLectura = baseDeDatosLectura.rawQuery(
            scripConsultarAutor,
            null
        )
        val existeAutor = resultadoConsultaLectura.moveToFirst()
        val autorEncontrado = Autor(0, "", "", "")
        if (existeAutor) {
            do {

                val id = resultadoConsultaLectura.getInt(0)
                val nombre = resultadoConsultaLectura.getString(1)
                val fecha = resultadoConsultaLectura.getString(2)
                val pais = resultadoConsultaLectura.getString(3)
                autorEncontrado.autor_id = id
                autorEncontrado.nombre_autor = nombre
                autorEncontrado.fecha_nac = fecha
                autorEncontrado.pais_nac = pais
            } while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDeDatosLectura.close()
        return autorEncontrado

    }

    fun actualizarAutorFormulario(
        id: Int,
        nombre: String,
        fecha: String,
        pais: String
    ): Boolean {
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("nombre", nombre)
        valoresAActualizar.put("fecha", fecha)
        valoresAActualizar.put("pais", pais)

        val resultaddoActualizacion = conexionEscritura
            .update(
                "autor",
                valoresAActualizar,
                "id_autor=?",
                arrayOf(
                    id.toString()
                )
            )
        conexionEscritura.close()
        return resultaddoActualizacion != -1

    }


    fun eliminarAutorFormulario(id: Int): Boolean {
        val conexionEscritura = writableDatabase
        val resultadoEliminacion = conexionEscritura
            .delete(
                "autor",
                "id_autor=?",
                arrayOf(
                    id.toString()
                )
            )
        return resultadoEliminacion != -1
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}
