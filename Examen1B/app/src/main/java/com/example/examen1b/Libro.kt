package com.example.examen1b

import android.os.Parcel
import android.os.Parcelable

class Libro(
    var titulo_libro: String?,
    var isbn: String?,
    var sinopsis: String?,
    var latitud: Double?,
    var longitud: Double?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readDouble()

    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(titulo_libro)
        parcel.writeString(isbn)
        parcel.writeString(sinopsis)
        latitud?.let { parcel.writeDouble(it) }
        longitud?.let { parcel.writeDouble(it) }
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "$titulo_libro, ISBN: $isbn"
    }

    companion object CREATOR : Parcelable.Creator<Libro> {
        override fun createFromParcel(parcel: Parcel): Libro {
            return Libro(parcel)
        }

        override fun newArray(size: Int): Array<Libro?> {
            return arrayOfNulls(size)
        }
    }
}