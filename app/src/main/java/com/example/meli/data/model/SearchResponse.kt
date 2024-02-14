package com.example.meli.data.model

import android.os.Parcel
import android.os.Parcelable

data class SearchResponse(
    val results: List<Product>?,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.createTypedArrayList(Product),
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(results)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SearchResponse> {
        override fun createFromParcel(parcel: Parcel): SearchResponse {
            return SearchResponse(parcel)
        }

        override fun newArray(size: Int): Array<SearchResponse?> {
            return arrayOfNulls(size)
        }
    }
}

