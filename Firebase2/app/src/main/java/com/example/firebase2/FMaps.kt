package com.example.firebase2

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolygonOptions
import com.google.android.gms.maps.model.PolylineOptions

class FMaps : AppCompatActivity() {
    private lateinit var mapa: GoogleMap
    var permisos = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fmaps)
        solicitarPermisos()

        val botonCarolina = findViewById<Button>(R.id.btn_ir_carolina)
        botonCarolina
            .setOnClickListener {
                val carolina = LatLng(-0.18288452555103193, -78.48449971346241)
                val zoom = 17f
                moverCamara(carolina, zoom)
            }

        val fragmentoMapa = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        fragmentoMapa.getMapAsync { googleMap ->
            if (googleMap != null) {
                mapa = googleMap
                configurarMapa()
                val quicentro = LatLng(-0.176125, -78.480208)
                val titulo = "Quicentro"
                val zoom = 17f
                moverCamara(quicentro, zoom)
                anadirMarcador(quicentro, titulo)

                val polygonOne=googleMap
                    .addPolygon(
                        PolygonOptions()
                            .clickable(true)
                            .add(
                                LatLng(-0.175327, -78.488211),
                                LatLng(-0.17158712906364446, -78.48483202848371),
                                LatLng(-0.17680657057716478, -78.47801941206454)
                            )
                    )
                polygonOne.fillColor= -0xc771c4
                polygonOne.tag="poligono-1"
                escucharListeners()

            }
        }
    }

    private fun escucharListeners() {
        mapa.setOnPolygonClickListener {
            Log.i("mapa", "$it")
        }
        mapa.setOnPolylineClickListener {
            Log.i("mapa", "$it")
        }
        mapa
            .setOnMarkerClickListener {
                Log.i("mapa", "$it")
                return@setOnMarkerClickListener true
            }
        mapa
            .setOnCameraMoveListener {
                Log.i("mapa", "")
            }
        mapa
            .setOnCameraMoveStartedListener {
                Log.i("mapa", "$it")
            }
        mapa
            .setOnCameraIdleListener {
                Log.i("mapa", "")
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