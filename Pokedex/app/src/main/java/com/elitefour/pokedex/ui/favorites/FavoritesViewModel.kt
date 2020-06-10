package com.elitefour.pokedex.ui.favorites

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elitefour.pokedex.PokedexApp
import com.elitefour.pokedex.managers.FavoritesManager
import com.elitefour.pokedex.model.Pokemon

class FavoritesViewModel: ViewModel() {


    private lateinit var favoritesManager: FavoritesManager

    var favoritesParseSuccess = MutableLiveData<Boolean>(false)

    fun init(favoritesManager: FavoritesManager) {
        this.favoritesManager = favoritesManager
    }

    fun getFavorites(): ArrayList<Pokemon>{
        return favoritesManager.favoritesList
    }


    fun clearFavorites(){
        favoritesManager.clearFavorites()
    }


    /**
     * @param pokemon the information about this pokemon to add to favorites
     * @return true if pokemon is fully initialized otherwise false
     */
    fun addFavorites(pokemon: Pokemon): Boolean {
        if (pokemon == null || pokemon.types.isNullOrEmpty()) {
            return false
        }
        favoritesManager.addFavorites(pokemon)
        return true
    }

    /**
     * @param pokemon the information about this pokemon to add to favorites
     * @return true if pokemon is fully initialized otherwise false
     */
    fun removeFavorites(pokemon: Pokemon): Boolean {
        if (pokemon == null || pokemon.types.isNullOrEmpty()) {
            return false
        }
        favoritesManager.removeFavorites(pokemon)
        return true
    }

    fun updateFavorites(pokemon: Pokemon) {
        if (containsFavorites(pokemon)) {
            removeFavorites(pokemon)
        } else {
            addFavorites(pokemon)
        }
    }

    fun containsFavorites(pokemon: Pokemon): Boolean {
        return favoritesManager.favoritesList.contains(pokemon)
    }


    /**
     * For use in settings fragment
     */
}