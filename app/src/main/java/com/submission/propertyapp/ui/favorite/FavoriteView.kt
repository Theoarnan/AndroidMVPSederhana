package com.submission.propertyapp.ui.favorite

import com.submission.propertyapp.model.PropertyData

interface FavoriteView {

    fun showLoading()
    fun hideLoading()
    fun showEmptyData()
    fun showEventList(data: MutableList<PropertyData>)
    fun showTeamList(data: MutableList<PropertyData>)
}