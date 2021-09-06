package com.example.firebase2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.firebase2.dto.FirestoreUsuarioDto
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {
    val CODIGO_INICIO_SESION = 102

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val botonLogin = findViewById<Button>(R.id.btn_login)
        botonLogin.setOnClickListener {
            llamarLoginUsuario()
        }


        val botonPedido = findViewById<Button>(R.id.btn_ir_pedido)
        botonPedido.setOnClickListener {
            abrirPedido()
        }


        val botonSalir = findViewById<Button>(R.id.btn_salir)
        botonSalir.setOnClickListener {
            solicitarSalirDelAplicativo()
        }

        val botonProducto = findViewById<Button>(R.id.btn_producto)
        botonProducto.setOnClickListener {
            irProducto()
        }

        val botonRestaurante = findViewById<Button>(R.id.btn_restaurante)
        botonRestaurante.setOnClickListener {
            irRestaurante()
        }
    }

    private fun abrirPedido() {
        val intent = Intent(
            this,
            Pedido::class.java
        )
        startActivity(intent)
    }

    private fun irRestaurante() {
        val intent = Intent(
            this,
            DRestaurante::class.java
        )
        startActivity(intent)
    }

    fun irProducto() {
        val intent = Intent(
            this,
            CProducto::class.java
        )
        startActivity(intent)
    }

    fun llamarLoginUsuario() {
        val providers = arrayListOf(
            // lista de los proveedores
            AuthUI.IdpConfig.EmailBuilder().build()
        )
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setTosAndPrivacyPolicyUrls(
                    "https://example.com/terms.html",
                    "https://example.com/privacy.html"
                )
                .build(),
            CODIGO_INICIO_SESION
        )
    }

    override fun onActivityResult(
        requestCode: Int, resultCode: Int, data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            CODIGO_INICIO_SESION -> {
                if (resultCode == Activity.RESULT_OK) {
                    val usuario: IdpResponse? = IdpResponse.fromResultIntent(data)
                    if (usuario != null) {
                        if (usuario.isNewUser == true) {
                            Log.i("firebase-login", "Nuevo usuario")
                            registrarUsuarioPorPrimeraVez(usuario)
                        } else {
                            setearUsuarioFirebase()
                            Log.i("firebase-login", "Usuario Antiguo")
                        }
                    }
                } else {
                    Log.i("firebase-login", "El usuario cancelo")
                }
            }
        }

    }

    fun registrarUsuarioPorPrimeraVez(usuario: IdpResponse) {
        val usuarioLogeado = FirebaseAuth
            .getInstance()
            .currentUser
        if (usuario.email != null && usuarioLogeado != null) {
            //roles : ["usuario", "admin"]
            //uid
            val db = Firebase.firestore // obtenemos referencia
            val rolesUsuario = arrayListOf("usuario") //creamos el arreglo de permisos
            val nuevoUsuario = hashMapOf<String, Any>( // {roles: ...... uid: .....}
                "roles" to rolesUsuario,
                "uid" to usuarioLogeado.uid,
                "email" to usuario.email.toString()
            )

            //identificador forma b
            val identificadorUsuario = usuario.email

            db.collection("usuario")
                //Forma a) Firestore crea identificador
                //.add(nuevoUsuario)
                //Forma b) yo seteo el identificador
                .document(identificadorUsuario.toString())
                .set(nuevoUsuario)
                .addOnSuccessListener {
                    Log.i("firebase-firestore", "Se creo")
                    setearUsuarioFirebase()
                }
                .addOnFailureListener {
                    Log.i("firebase-firestore", "Fallo")
                }
        }
    }

    fun setearUsuarioFirebase() {
        val instanciaAuth = FirebaseAuth.getInstance()
        val usuariolocal = instanciaAuth.currentUser
        if (usuariolocal != null) {
            if (usuariolocal.email != null) {
                //buscar en el firestore el usuario y traerlo con todos los datos
                val db = Firebase.firestore
                val referencia = db
                    .collection("usuario")
                    .document(usuariolocal.email.toString())

                referencia
                    .get()
                    .addOnSuccessListener {
                        val usuarioCargado = it.toObject(FirestoreUsuarioDto::class.java)
                        if (usuarioCargado != null) {
                            DAuthUsuario.usuario = BUsuarioFirebase(
                                usuarioCargado.uid,
                                usuarioCargado.email,
                                usuarioCargado.roles,
                            )
                            setearBienvenida()
                        }
                        Log.i("firebase-firestore", "Usuario cargado")
                    }
                    .addOnFailureListener {
                        Log.i("firebase-firestore", "Fallo cargar usuario")
                    }
            }
        }
    }

    fun setearBienvenida() {
        val textViewBienvenida = findViewById<TextView>(R.id.tv_bienvenida)
        val botonLogin = findViewById<Button>(R.id.btn_login)
        val botonSalir = findViewById<Button>(R.id.btn_salir)
        val botonProducto = findViewById<Button>(R.id.btn_producto)
        val botonRestaurante = findViewById<Button>(R.id.btn_restaurante)
        if (DAuthUsuario.usuario != null) {
            textViewBienvenida.text = "Bienvenido ${DAuthUsuario.usuario?.email}"
            botonLogin.visibility = View.INVISIBLE
            botonSalir.visibility = View.VISIBLE
            botonProducto.visibility = View.VISIBLE
            botonRestaurante.visibility=View.VISIBLE
        } else {
            textViewBienvenida.text = "Ingresa al aplicativo"
            botonLogin.visibility = View.VISIBLE
            botonSalir.visibility = View.INVISIBLE
            botonProducto.visibility = View.INVISIBLE
        }
    }

    fun solicitarSalirDelAplicativo() {
        AuthUI
            .getInstance()
            .signOut(this)
            .addOnCompleteListener {
                DAuthUsuario.usuario = null
                setearBienvenida()
            }
    }
}