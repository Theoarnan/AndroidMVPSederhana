package com.submission.propertyapp.view

interface CommonView {

    fun showLoading()
    fun error(error : Throwable)
    fun success(anyResponse: Any)
    fun hideLoading()
}