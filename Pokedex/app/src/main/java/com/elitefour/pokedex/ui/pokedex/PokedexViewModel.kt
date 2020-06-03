package com.elitefour.pokedex.ui.pokedex

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.elitefour.pokedex.managers.APIManager
import com.elitefour.pokedex.model.Pokemon
import com.elitefour.pokedex.model.PokemonFullInfo
import com.elitefour.pokedex.model.Type

class PokedexViewModel : ViewModel(){

    private lateinit var apiManager: APIManager

    var currPokemonInfo = MutableLiveData<PokemonFullInfo>()
    var pokemonList = MutableLiveData<ArrayList<Pokemon>>()
    var failure = MutableLiveData<Boolean>()

    fun init(apiManager: APIManager) {
        this.apiManager = apiManager
        var type = "hello"
        apiManager.fetchTypeList({ resultList ->
            Log.i("elite", resultList.toString())
            type = resultList.get(5).url;
            apiManager.fetchTypeInfo(type, { typeFullInfo ->
                Log.i("elite", typeFullInfo.toString())
            }, {
                failure.value = true
            })
        }, {
            failure.value = true
        })

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
    fun updatePokemonList() {
        apiManager.fetchPokemonList ({ resultList ->
            pokemonList.value = resultList as ArrayList<Pokemon>
        }, {
            failure.value = true
        })
    }

    /**
     * Update the current information of the pokemon
     * @param url The url requesting the detail of a pokemon.
     */
    fun updatePokemonInfo(url: String) {
        apiManager.fetchPokemonInfo (url, { pokemonFullInfo ->
            currPokemonInfo.value = pokemonFullInfo
        }, {
            failure.value = true
        })
    }
}