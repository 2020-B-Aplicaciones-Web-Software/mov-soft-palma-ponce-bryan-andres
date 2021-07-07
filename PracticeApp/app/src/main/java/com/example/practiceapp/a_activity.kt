package com.example.practica1moviles

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.practiceapp.R
import com.example.practiceapp.b_activity

class a_activity : AppCompatActivity() {
    var numero = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a_activity)
        Log.i("CicloVida", "OnCreate")
        val botonCicloVida = findViewById<Button>(
            R.id.btn_ciclo_vida
        )
        actualizarEnPantalla()
        botonCicloVida.setOnClickListener {
            aumentarNumero()
            actualizarEnPantalla()
        }





    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.run {
            putInt("NumeroGuardado", numero)
        }
        super.onSaveInstanceState(outState)
        Log.i("CicloVida", "OnSaveInstance")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val numeroRecuperado: Int? = savedInstanceState.getInt("NumeroGuardado")
        if (numeroRecuperado != null) {
            Log.i("CicloVida", "Numero${numeroRecuperado}")
            numero = numeroRecuperado
            actualizarEnPantalla()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i("CicloVida", "OnStart")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("CicloVida", "OnRestart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("CicloVida", "OnResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("CicloVida", "OnPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("CicloVida", "OnStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("CicloVida", "OnDestroy")
    }

    fun aumentarNumero() {
        numero++
    }

    fun actualizarEnPantalla() {
        val textViewCicloVida = findViewById<TextView>(
            R.id.txv_ciclo_vida
        )
        textViewCicloVida.text = numero.toString()
    }
}