package com.submission.propertyapp.util.bottomSheet

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.arthurivanets.bottomsheets.sheets.model.Option
import com.submission.propertyapp.R

fun Context.createOption(id: Long,
                         @DrawableRes iconId: Int,
                         title : CharSequence) : Option {
    return Option()
        .setId(id)
        .setIconId(iconId)
        .setIconColor(ContextCompat.getColor(this, R.color.colorSecondaryTextGray))
        .setTitle(title)
        .setTitleColor(ContextCompat.getColor(this, R.color.colorSecondaryTextBlack))
}