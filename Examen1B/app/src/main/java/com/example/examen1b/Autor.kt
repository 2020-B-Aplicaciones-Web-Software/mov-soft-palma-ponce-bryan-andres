package com.example.examen1b

import android.os.Parcel
import android.os.Parcelable

class Autor(
    var nombre_autor: String?,
    var fecha_nac: String?,
    var pais_nac: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        p0?.writeString(nombre_autor)
        p0?.writeString(fecha_nac)
        p0?.writeString(pais_nac)
    }

    override fun toString(): String {
        return "$nombre_autor\nFecha de nacimiento: $fecha_nac\nPais: $pais_nac"
    }

    companion object CREATOR : Parcelable.Creator<Autor> {
        override fun createFromParcel(parcel: Parcel): Autor {
            return Autor(parcel)
        }

        override fun newArray(size: Int): Array<Autor?> {
            return arrayOfNulls(size)
        }
    }


}
