package com.example.practiceapp

import android.os.Parcel
import android.os.Parcelable

class Autor(
    var autor_id: Int,
    var nombre: String?,
    var pais: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(autor_id)
        parcel.writeString(nombre)
        parcel.writeString(pais)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "$nombre - $pais"
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