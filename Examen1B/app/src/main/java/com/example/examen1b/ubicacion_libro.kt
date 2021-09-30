package com.example.examen1b

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class ubicacion_libro : AppCompatActivity() {
    private lateinit var mapa: GoogleMap
    var permisos = false
    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 401
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ubicacion_libro)
        val libro = intent.getParcelableExtra<Libro>("libro")
        if (libro != null) {
            findViewById<TextView>(R.id.titulo_libro_ubicacion).text = libro.titulo_libro
        }
        solicitarPermisos()
        val fragmentoMapa = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        fragmentoMapa.getMapAsync { googleMap ->
            mapa = googleMap
            configurarMapa()
            val ubicacionLibro = libro?.latitud?.let {
                libro.longitud?.let { it1 ->
                    LatLng(
                        it,
                        it1
                    )
                }
            }
            val titulo = libro?.titulo_libro
            val zoom = 17f
            if (ubicacionLibro != null && titulo != null) {
                Log.d("mpa",ubicacionLibro.toString())
                moverCamara(ubicacionLibro, zoom)
                anadirMarcador(ubicacionLibro, titulo)
            }


        }
    }


    private fun anadirMarcador(LatLng: LatLng, title: String) {
        mapa.addMarker(
            MarkerOptions()
                .position(LatLng)
                .title(title)
        )
    }

    fun moverCamara(LatLng: LatLng, zoom: Float = 10f) {
        mapa.moveCamera(
            CameraUpdateFactory
                .newLatLngZoom(LatLng, zoom)
        )
    }

    private fun solicitarPermisos() {
        val contexto = this.applicationContext
        val permisoFineLocation = ContextCompat
            .checkSelfPermission(
                contexto,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
        val tienePermisos = permisoFineLocation == PackageManager.PERMISSION_GRANTED
        if (tienePermisos) {
            permisos = true
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                1
            )
        }
    }

    private fun configurarMapa() {
        val contexto = this.applicationContext
        with(mapa) {
            val permisoFineLocation = ContextCompat
                .checkSelfPermission(
                    contexto,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                )
            val tienePermisos = permisoFineLocation == PackageManager.PERMISSION_GRANTED
            if (tienePermisos) {
                mapa.isMyLocationEnabled = true
            }
            uiSettings.isZoomControlsEnabled = true
            uiSettings.isMyLocationButtonEnabled = true
        }
    }
}
