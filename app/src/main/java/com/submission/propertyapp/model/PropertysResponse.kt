package com.submission.propertyapp.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class PropertysResponse(
    @SerializedName("meta")
    val meta: Meta,
    @SerializedName("property_data")
    val propertyData: MutableList<PropertyData>
)

data class PropertyData(
    @SerializedName("id_property")
    var idProperty : String?,
    @SerializedName("nama_property")
    var namaProperty : String?,
    @SerializedName("harga_property")
    var hargaProperty : String?,
    @SerializedName("stock_property")
    var stockProperty : String?,
    @SerializedName("detail_property")
    var detailProperty : String?,
    @SerializedName("lokasi_property")
    var lokasiProperty : String?,
    @SerializedName("gambar_property")
    var gambarProperty: String?,
//    @SerializedName("tipe_property")
//    var tipeProperty: String,
    @SerializedName("image_url")
    var imageUrl : String?,
    @SerializedName("created_at")
    var createdAt : String?,
    @SerializedName("updated_at")
    var updatedAt : String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }



    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(idProperty)
        parcel.writeString(namaProperty)
        parcel.writeString(hargaProperty)
        parcel.writeString(stockProperty)
        parcel.writeString(detailProperty)
        parcel.writeString(lokasiProperty)
        parcel.writeString(gambarProperty)
        parcel.writeString(imageUrl)
        parcel.writeString(createdAt)
        parcel.writeString(updatedAt)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PropertyData> {
        override fun createFromParcel(parcel: Parcel): PropertyData {
            return PropertyData(parcel)
        }

        override fun newArray(size: Int): Array<PropertyData?> {
            return arrayOfNulls(size)
        }
    }
}