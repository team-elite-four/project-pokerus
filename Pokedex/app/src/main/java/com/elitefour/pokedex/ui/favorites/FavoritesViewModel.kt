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

    fun addFavorites(pokemon: Pokemon) {
        favoritesManager.addFavorites(pokemon)
        favoritesModified.value = favoritesModified.value?.plus(1)
    }

    fun removeFavorites(pokemon: Pokemon) {
        favoritesManager.removeFavorites(pokemon)
        favoritesModified.value = favoritesModified.value?.plus(1)
    }


    /**
     * For use in settings fragment
     */
}