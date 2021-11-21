package com.example.myapplication.models

import android.os.Parcel
import android.os.Parcelable


data class ProductResponseItem(
    val created_at: String,
    val id: Int,
    val image: String,
    val name: String,
    val price: Int,
    val stock: Int,
    val updated_at: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(created_at)
        parcel.writeInt(id)
        parcel.writeString(image)
        parcel.writeString(name)
        parcel.writeInt(price)
        parcel.writeInt(stock)
        parcel.writeString(updated_at)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProductResponseItem> {
        override fun createFromParcel(parcel: Parcel): ProductResponseItem {
            return ProductResponseItem(parcel)
        }

        override fun newArray(size: Int): Array<ProductResponseItem?> {
            return arrayOfNulls(size)
        }
    }
}

data class ProductRequest(
    val name: String,
    val price: Int,
    val stock: Int,
)