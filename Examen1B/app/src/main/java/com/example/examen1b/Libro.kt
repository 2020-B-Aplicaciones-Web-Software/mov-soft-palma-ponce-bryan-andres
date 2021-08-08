package com.example.examen1b

import android.os.Parcel
import android.os.Parcelable

class Libro(
    var id_libro: Int,
    var titulo_libro: String?,
    var isbn: String?,
    var sinopsis: String?,
    var autor: Autor?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readParcelable(Autor::class.java.classLoader)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id_libro)
        parcel.writeString(titulo_libro)
        parcel.writeString(isbn)
        parcel.writeString(sinopsis)
        parcel.writeParcelable(autor, flags)
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