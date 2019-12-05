package com.submission.propertyapp.view

interface CommonViewDetail {

    fun showLoading()
    fun error(error : Throwable)
    fun hideLoading()
}