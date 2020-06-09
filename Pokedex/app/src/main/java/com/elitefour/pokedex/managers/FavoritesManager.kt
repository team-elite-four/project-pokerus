package com.elitefour.pokedex.managers

import android.content.Context
import com.elitefour.pokedex.PokedexApp
import com.elitefour.pokedex.model.FavoritesCollection
import com.elitefour.pokedex.model.Pokemon
import com.elitefour.pokedex.model.PokemonCollection
import com.google.gson.Gson
import org.json.JSONObject
import java.io.IOException

class FavoritesManager(context: Context) {
    var favoritesReady: Boolean = false
    private var favoritesList: ArrayList<Pokemon> = ArrayList()
    private var preferenceManager: PreferenceManager = PokedexApp.getApp(context).preferenceManager


    init {
        preferenceManager.getStringPreference(KEY)?.let {
            favoritesList = Gson().fromJson(it, FavoritesCollection::class.java).favorites
            favoritesReady = true
        }

    }

    fun clearFavorites() {
        preferenceManager.removeStringPreference(KEY)
    }

    fun addFavorites(pokemon: Pokemon) {
        favoritesList.add(pokemon)
        updateFavorites()
    }

    fun removeFavorites(pokemon: Pokemon) {
        favoritesList.remove(pokemon)
        updateFavorites()
    }

    private fun updateFavorites() {
        preferenceManager.editStringPreference(KEY, toJsonText())
    }

    private fun toJsonText(): String {
        return Gson().toJson(FavoritesCollection(favoritesList))
    }

    companion object {
        val TAG = FavoritesManager::class.simpleName
        val KEY = "FAVORITES"
    }

}