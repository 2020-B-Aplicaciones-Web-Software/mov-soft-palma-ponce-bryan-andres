package com.example.practiceapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class ESqliteHelperUsuario(
    contexto: Context?
):SQLiteOpenHelper(
    contexto,
    "moviles",
    null,
    1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptCrearTablaUsuario=
            """
                CREATE TABLA USUARIO(
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nombre VARCHAR(50),
                    descripcion VARCHAR(50)
            """.trimIndent()
        Log.i("bdd",scriptCrearTablaUsuario)
        db?.execSQL(scriptCrearTablaUsuario)
    }
    fun crearUsuarioFormulario(
        nombre:String,
        descripcion:String
    ):Boolean{
        val conexionEscritura = writableDatabase
        val valoresAGuardar=ContentValues()
        valoresAGuardar.put("nombre",nombre)
        valoresAGuardar.put("descripcion",descripcion)
        val resultadoEscritura: Long = conexionEscritura
            .insert(
                "USUARIO",
                null,
                valoresAGuardar
            )
        conexionEscritura.close()
        return resultadoEscritura.toInt() != -1
    }
    fun consultarUsuarioPorId(id:Int):EUsuarioBDD{
        val scripConsultarUsuario="SELECT * FROM USUARIO WHERE ID = ${id}"
        val baseDeDatosLectura=readableDatabase
        val resultadoConsultaLectura=baseDeDatosLectura.rawQuery(
            scripConsultarUsuario,
            null
        )
        return EUsuarioBDD(1,"","")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }


}