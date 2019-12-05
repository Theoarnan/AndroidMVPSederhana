package com.submission.propertyapp.presenter

import android.content.Context
import com.submission.propertyapp.model.PropertyData
import com.submission.propertyapp.networking.RetrofitFactory
import com.submission.propertyapp.view.CommonView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AllPresenter(
    private val view : CommonView,
    private val context : Context
)
{
    fun getToken(auth: String){
        view.showLoading()
        val api = RetrofitFactory.create()
        val request = api.getToken(auth)
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val response = request.await()
                view.success(response)
            }catch (e : Exception){
                view.error(e)
            }
            view.hideLoading()
        }
    }

    fun getAllProperty(){
        view.showLoading()
        val api = RetrofitFactory.create(context)
        val request = api.getAllProperty()
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val response = request.await()
                view.success(response)
            }catch (e : Exception){
                view.error(e)
            }
            view.hideLoading()
        }
    }

    fun createProperty(property  : PropertyData){
        view.showLoading()
        val api = RetrofitFactory.create(context)
        val request = api.createProperty(
            property.namaProperty.toString(),
            property.hargaProperty.toString(),
            property.stockProperty.toString(),
            property.detailProperty.toString(),
            property.lokasiProperty.toString(),
            property.gambarProperty.toString()
        )
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val response = request.await()
                view.success(response)
            }catch (e : Exception){
                view.error(e)
            }
            view.hideLoading()
        }
    }

    //Delete
    fun deleteProperty(id  : String){
        view.showLoading()
        val api = RetrofitFactory.create(context)
        val request = api.deleteProperty(id)
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val response = request.await()
                view.success(response)
            }catch (e : Exception){
                view.error(e)
            }
            view.hideLoading()
        }
    }

    fun updateProperty(property: PropertyData){
        view.showLoading()
        val api = RetrofitFactory.create(context)
        val request = api.updateProperty(property)
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val response = request.await()
                view.success(response)
            }catch (e : Exception){
                view.error(e)
            }
            view.hideLoading()
        }
    }

}