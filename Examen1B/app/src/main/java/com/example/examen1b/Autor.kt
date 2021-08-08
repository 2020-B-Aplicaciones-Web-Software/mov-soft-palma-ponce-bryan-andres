package com.example.examen1b

import android.os.Parcel
import android.os.Parcelable

class Autor(
    var autor_id: Int,
    var nombre_autor: String?,
    var fecha_nac: String?,
    var pais_nac: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
    )

        override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(autor_id)
        parcel.writeString(nombre_autor)
        parcel.writeString(fecha_nac)
        parcel.writeString(pais_nac)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "$nombre_autor"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Autor

        if (autor_id != other.autor_id) return false
        if (nombre_autor != other.nombre_autor) return false
        if (fecha_nac != other.fecha_nac) return false
        if (pais_nac != other.pais_nac) return false

        return true
    }

    override fun hashCode(): Int {
        var result = autor_id
        result = 31 * result + (nombre_autor?.hashCode() ?: 0)
        result = 31 * result + (fecha_nac?.hashCode() ?: 0)
        result = 31 * result + (pais_nac?.hashCode() ?: 0)
        return result
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
