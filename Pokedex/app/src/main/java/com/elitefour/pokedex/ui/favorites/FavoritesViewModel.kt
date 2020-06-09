package com.elitefour.pokedex.ui.favorites

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elitefour.pokedex.managers.FavoritesManager
import com.elitefour.pokedex.model.Pokemon

class FavoritesViewModel: ViewModel() {


    private lateinit var favoritesManager: FavoritesManager

    var favoritesParseSuccess = MutableLiveData<Boolean>(false)
    var favoritesModified = MutableLiveData<Int>(0)

    fun getFavorites(): ArrayList<Pokemon>{
        return ArrayList()
        favoritesParseSuccess.value = true
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
        favoritesModified.value = favoritesModified.value?.plus(1)
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
        favoritesModified.value = favoritesModified.value?.plus(1)
        return true
    }


    /**
     * For use in settings fragment
     */
}