package com.elitefour.pokedex.ui.pokedex

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elitefour.pokedex.interfaces.OnPokedexReadyListener

import com.elitefour.pokedex.managers.APIManager
import com.elitefour.pokedex.managers.PokedexManager
import com.elitefour.pokedex.model.Pokemon
import com.elitefour.pokedex.model.PokemonFullInfo

class PokedexViewModel : ViewModel(), OnPokedexReadyListener{

    private lateinit var apiManager: APIManager
    private lateinit var pokedexManager: PokedexManager

//    var pokemonList = MutableLiveData<ArrayList<Pokemon>>()
    var pokemonListFetchSuccess = MutableLiveData<Boolean>()


    fun init(apiManager: APIManager, pokedexManager: PokedexManager) {
        this.apiManager = apiManager
        this.pokedexManager = pokedexManager
        if (!pokedexManager.dataReady) {
            pokedexManager.onPokedexReadyListener = this
        }
    }

    /**
     * @param id The id of the pokemon
     * @return The image url of the sprite of the pokemon
     */
    fun getPokemonImageResource(id: Int): String {
        return apiManager.fetchPokemonImageURL(id)
    }

    /**
     * Update the current list of pokemon
     */
    fun getPokemonList(): List<Pokemon> {
        return pokedexManager.getPokemonList()
    }

//    /**
//     * Update the current information of the pokemon
//     * @param url The url requesting the detail of a pokemon.
//     */
//    fun updatePokemonInfo(url: String) {
//        apiManager.fetchPokemonFullInfo (url, { pokemonFullInfo ->
//            currPokemonInfo.value = pokemonFullInfo
//        }, {
//            failure.value = true
//        })
//    }


    override fun ready() {
        pokemonListFetchSuccess.value = true
    }
}