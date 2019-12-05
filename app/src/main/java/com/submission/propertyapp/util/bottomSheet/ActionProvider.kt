package com.submission.propertyapp.util.bottomSheet

import android.content.Context
import com.arthurivanets.bottomsheets.sheets.model.Option
import com.submission.propertyapp.R

object ActionProvider {

    object Id {
        const val EDIT = 1L
        const val DELETE = 2L
    }

    fun getAcionOptions(context: Context): List<Option> {
        return ArrayList<Option>().apply {
            add(
                context.createOption(
                    Id.EDIT,
                    R.drawable.ic_edit,
                    context.getString(R.string.action_edit)
                )
            )
            add(
                context.createOption(
                    Id.DELETE,
                    R.drawable.ic_delete,
                    context.getString(R.string.action_delete)
                )
            )
        }
    }
}