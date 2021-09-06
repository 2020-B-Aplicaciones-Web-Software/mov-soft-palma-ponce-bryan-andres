package com.example.prototipo

import android.os.Parcel
import android.os.Parcelable

class MessageClass(
    val header:String?,
    val info:String?,
    val user:String?,
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(header)
        parcel.writeString(info)
        parcel.writeString(user)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MessageClass> {
        override fun createFromParcel(parcel: Parcel): MessageClass {
            return MessageClass(parcel)
        }

        override fun newArray(size: Int): Array<MessageClass?> {
            return arrayOfNulls(size)
        }
    }
}