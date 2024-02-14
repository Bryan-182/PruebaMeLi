package com.example.meli.data.model

import android.os.Parcel
import android.os.Parcelable

data class Seller(
    val id: Long,
    val nickname: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString()
    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeLong(id)
        dest.writeString(nickname)
    }

    companion object CREATOR : Parcelable.Creator<Seller> {
        override fun createFromParcel(parcel: Parcel): Seller {
            return Seller(parcel)
        }

        override fun newArray(size: Int): Array<Seller?> {
            return arrayOfNulls(size)
        }
    }
}