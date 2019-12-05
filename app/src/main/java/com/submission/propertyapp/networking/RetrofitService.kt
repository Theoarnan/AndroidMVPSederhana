package com.submission.propertyapp.networking

import com.submission.propertyapp.model.LoginResponse
import com.submission.propertyapp.model.PropertyData
import com.submission.propertyapp.model.PropertysResponse
import com.submission.propertyapp.model.SuccessResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.*

interface RetrofitService {

    @POST("Auth")
    fun getToken(
        @Header("Authorization") basic: String
    ): Deferred<LoginResponse>

    @GET("Property")
    fun getAllProperty(): Deferred<PropertysResponse>

    @GET("Property")
    fun getAllPropertySearch(@Query("Search")type: String)
            : Deferred<PropertysResponse>
//
//    @GET("Property/{id_property}")
//    fun getAllPropertyById(@Path("id_property")type: String): Deferred<PropertyDetailResponse>

//    @GET("Property")
//    fun getEventsSearch(eventName: String): String {
//        return "/searchevents.php?e=$eventName"
//    }

    @FormUrlEncoded
    @POST("Property")
    fun createProperty(
        @Field("nama_property") namaProperty: String,
        @Field("harga_property") hargaProperty: String,
        @Field("stock_property") stockProperty: String,
        @Field("detail_property") detailProperty: String,
        @Field("lokasi_property") lokasiProperty: String,
        @Field("gambar_property") gambarProperty: String
    ): Deferred<SuccessResponse>

    @PUT("Property")
    fun updateProperty(@Body property : PropertyData) : Deferred<SuccessResponse>

    @DELETE("Property/{id}")
    fun deleteProperty(@Path("id") idProperty : String) : Deferred<SuccessResponse>
}