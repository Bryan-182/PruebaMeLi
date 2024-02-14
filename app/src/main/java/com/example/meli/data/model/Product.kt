package com.example.meli.data.model

import android.os.Parcel
import android.os.Parcelable

data class Product(
    val id: String?,
    val title: String?,
    val condition: String?,
    val thumbnail: String?,
    val currencyID: String?,
    val price: Double,
    val availableQuantity: Long,
    val acceptsMercadopago: Boolean,
    val shipping: Shipping?,
    val seller: Seller?,
    val attributes: List<Attribute>?,
    val installments: Installments?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readLong(),
        parcel.readByte() != 0.toByte(),
        parcel.readParcelable(Shipping::class.java.classLoader),
        parcel.readParcelable(Seller::class.java.classLoader),
        parcel.createTypedArrayList(Attribute),
        parcel.readParcelable(Installments::class.java.classLoader)
    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(id)
        dest.writeString(title)
        dest.writeString(condition)
        dest.writeString(thumbnail)
        dest.writeString(currencyID)
        dest.writeDouble(price)
        dest.writeLong(availableQuantity)
        dest.writeByte(if (acceptsMercadopago) 1 else 0)
        dest.writeParcelable(shipping, flags)
        dest.writeParcelable(seller, flags)
        dest.writeTypedList(attributes)
        dest.writeParcelable(installments, flags)
    }

    companion object CREATOR : Parcelable.Creator<Product> {
        override fun createFromParcel(parcel: Parcel): Product {
            return Product(parcel)
        }

        override fun newArray(size: Int): Array<Product?> {
            return arrayOfNulls(size)
        }
    }
}
