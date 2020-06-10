package com.elitefour.pokedex.managers

import android.content.Context
import com.elitefour.pokedex.MainActivity

class PreferenceManager(private var context: Context) {
    private val sharedPreferences = context.getSharedPreferences(MainActivity.PREF, Context.MODE_PRIVATE)

    fun editStringPreference(tag: String, input: String) {
        sharedPreferences.edit().putString(tag, input).apply()
    }

    fun getStringPreference(tag: String): String? {
        return sharedPreferences.getString(tag, null)
    }

    fun removeStringPreference(tag: String) {
        sharedPreferences.edit().remove(tag).apply()
    }
}