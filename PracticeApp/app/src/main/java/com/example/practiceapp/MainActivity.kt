package com.example.practiceapp

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.practica1moviles.a_activity
import com.example.practiceapp.examen.vista_autor

class MainActivity : AppCompatActivity() {
    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 401
    val CODIGO_RESPUESTA_INTENT_IMPLICITO = 402
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val btn_ir_autores = findViewById<Button>(
            R.id.btn_trabajo_sql
        )
        btn_ir_autores.setOnClickListener {
            val intentExplicito = Intent(
                this,
                vista_autor::class.java
            )
            startActivity(intentExplicito)
        }


        val btn_ir_http = findViewById<Button>(
            R.id.btn_ir_http
        )
        btn_ir_http.setOnClickListener {
            abrirCicloVida(
                activity_http::class.java
            )
        }

        val btn_ir_recycler_view = findViewById<Button>(
            R.id.btn_ir_recycler_view
        )
        btn_ir_recycler_view.setOnClickListener {
            abrirActividadConParametros(GRecyclerView::class.java)
        }

        val btn_ir_ciclo_vida2 = findViewById<Button>(
            R.id.btn_ir_list_view2
        )
        btn_ir_ciclo_vida2.setOnClickListener {
            val intentExplicito = Intent(
                this,
                b_activity::class.java
            )
            startActivity(intentExplicito)
        }


        val btn_intent_explicito = findViewById<Button>(
            R.id.btn_ir_intent
        )
        btn_intent_explicito.setOnClickListener {
            abrirActividadConParametros(CIntentExplicitoParametros::class.java)
        }

        val btn_intent_implicito = findViewById<Button>(
            R.id.btn_intent_implicito
        )
        btn_intent_implicito
            .setOnClickListener {
                val intentConRespuestaImplicito = Intent(
                    Intent.ACTION_PICK,
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                )
                startActivityForResult(
                    intentConRespuestaImplicito,
                    CODIGO_RESPUESTA_INTENT_IMPLICITO
                )
            }


    }


    fun abrirActividadConParametros(
        clase: Class<*>
    ) {
        val intentExplito = Intent(
            this,
            clase
        )
        intentExplito.putExtra("nombre", "Andres")
        intentExplito.putExtra("apellido", "Palma")
        intentExplito.putExtra("edad", 21)
        intentExplito.putExtra("Entrenador", BEntrenador("Pedro", "Perez"))
        startActivityForResult(intentExplito, CODIGO_RESPUESTA_INTENT_EXPLICITO)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            CODIGO_RESPUESTA_INTENT_EXPLICITO ->
                if (resultCode == Activity.RESULT_OK) {
                    Log.i("intentE", "Datos actualizados")
                    if (data != null) {
                        val nombre = data.getStringExtra("NombreModificado")
                        val edad = data.getIntExtra("EdadModificada", 0)
                        Log.i("intentE", "$nombre")
                        Log.i("intentE", "$edad")
                    }
                }
            CODIGO_RESPUESTA_INTENT_IMPLICITO -> {
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        val uri: Uri = data.data!!
                        val cursor = contentResolver.query(
                            uri,
                            null,
                            null,
                            null,
                            null,
                            null
                        )
                        cursor?.moveToFirst()
                        val indiceTelefono = cursor?.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.NUMBER
                        )
                        val telefono = cursor?.getString(
                            indiceTelefono!!
                        )
                        cursor?.close()
                        Log.i("resultado", "Telefono ${telefono}")
                    }
                }
            }

        }

    }

    fun abrirCicloVida(
        clase: Class<*>
    ) {
        val intentExplicito = Intent(
            this,
            clase
        )
        startActivity(intentExplicito)
    }
}