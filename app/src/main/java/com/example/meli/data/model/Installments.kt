package com.example.meli.data.model

import android.os.Parcel
import android.os.Parcelable

data class Installments(
    val quantity: Int,
    val amount: Double,
    val rate: Double,
    val currencyId: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readString()
    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(quantity)
        dest.writeDouble(amount)
        dest.writeDouble(rate)
        dest.writeString(currencyId)
    }

    companion object CREATOR : Parcelable.Creator<Installments> {
        override fun createFromParcel(parcel: Parcel): Installments {
            return Installments(parcel)
        }

        override fun newArray(size: Int): Array<Installments?> {
            return arrayOfNulls(size)
        }
    }
}