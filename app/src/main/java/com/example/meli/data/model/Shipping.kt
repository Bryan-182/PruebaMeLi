package com.example.meli.data.model

import android.os.Parcel
import android.os.Parcelable

data class Shipping(
    val storePickUp: Boolean,
    val freeShipping: Boolean,
    val logisticType: String?,
    val mode: String?,
    val tags: List<String>?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.readString(),
        parcel.readString(),
        parcel.createStringArrayList()
    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeByte(if (storePickUp) 1 else 0)
        dest.writeByte(if (freeShipping) 1 else 0)
        dest.writeString(logisticType)
        dest.writeString(mode)
        dest.writeStringList(tags)
    }

    companion object CREATOR : Parcelable.Creator<Shipping> {
        override fun createFromParcel(parcel: Parcel): Shipping {
            return Shipping(parcel)
        }

        override fun newArray(size: Int): Array<Shipping?> {
            return arrayOfNulls(size)
        }
    }
}
