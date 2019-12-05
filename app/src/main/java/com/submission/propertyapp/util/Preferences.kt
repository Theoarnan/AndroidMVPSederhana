package com.submission.propertyapp.util

import android.content.Context
import android.preference.PreferenceManager

object Preferences {

    fun saveToken(token: String, context: Context): Boolean {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val prefsEditor = prefs.edit()
        prefsEditor.putString(Const.KEY_TOKEN, token)
        prefsEditor.apply()
        return true
    }

    fun getToken(context: Context): String? {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        return  prefs.getString(Const.KEY_TOKEN, "")
    }

    fun saveUsername(token: String, context: Context): Boolean {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val prefsEditor = prefs.edit()
        prefsEditor.putString(Const.KEY_USERNAME, token)
        prefsEditor.apply()
        return true
    }

    fun getUsername(context: Context): String? {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        return  prefs.getString(Const.KEY_USERNAME, "")
    }

    fun savePassword(token: String, context: Context): Boolean {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val prefsEditor = prefs.edit()
        prefsEditor.putString(Const.KEY_PASSWORD, token)
        prefsEditor.apply()
        return true
    }
    fun getPassword(context: Context): String? {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        return  prefs.getString(Const.KEY_PASSWORD, "")
    }
}