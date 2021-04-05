package com.example.test.data.area

import android.os.Parcel
import android.os.Parcelable

data class AreaData(
    val _id: Long? = null,
    val E_no: Long? = null,
    val E_Name: String,
    val E_Pic_URL: String,
    val E_Geo: String? = null,
    val E_Info: String,
    val E_Category: String? = null,
    val E_Memo: String? = null,
    val E_URL: String? = null
)

data class ParcelableAreaData(val E_Name: String, val E_Pic_URL: String, val E_Info: String) :
    Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(E_Name)
        parcel.writeString(E_Pic_URL)
        parcel.writeString(E_Info)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ParcelableAreaData> {
        override fun createFromParcel(parcel: Parcel): ParcelableAreaData {
            return ParcelableAreaData(parcel)
        }

        override fun newArray(size: Int): Array<ParcelableAreaData?> {
            return arrayOfNulls(size)
        }
    }
}