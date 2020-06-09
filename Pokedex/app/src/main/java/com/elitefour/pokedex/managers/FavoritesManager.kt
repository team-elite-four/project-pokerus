package com.elitefour.pokedex.managers

import android.content.Context
import com.elitefour.pokedex.model.FavoritesCollection
import com.elitefour.pokedex.model.Pokemon
import com.google.gson.Gson
import java.io.IOException

class FavoritesManager(context: Context) {
    var favoritesReady: Boolean = false
    private var favoritesList: ArrayList<Pokemon> = ArrayList()





    private fun getGsonFromAsset(context: Context, fileName: String): ArrayList<Pokemon>? {
        var jsonString: String? = null
        val gson = Gson()
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
        }
        return gson.fromJson(jsonString, FavoritesCollection::class.java).favorites
    }

    fun clearFavorites() {

    }

    fun addFavorites(pokemon: Pokemon) {
        favoritesList.add(pokemon)
    }

    fun removeFavorites(pokemon: Pokemon) {
        favoritesList.remove(pokemon)
    }
}