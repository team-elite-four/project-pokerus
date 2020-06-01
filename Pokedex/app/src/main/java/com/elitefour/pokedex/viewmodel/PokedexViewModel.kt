package com.elitefour.pokedex.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.elitefour.pokedex.managers.APIManager
import com.elitefour.pokedex.model.Pokemon

class PokedexViewModel : ViewModel(){

    private lateinit var apiManager: APIManager

    var pokemonList = MutableLiveData<ArrayList<Pokemon>>()
    var failure = MutableLiveData<Boolean>()

    fun init(apiManager: APIManager) {
        this.apiManager = apiManager
    }

    fun getPokemonImageResource(number: Int): String {
        return apiManager.fetchPokemonImageURL(number)
    }

    fun updatePokemonList() {
        apiManager.fetchPokemonList ({ resultList ->
            pokemonList.value = resultList as ArrayList<Pokemon>
        }, {
            failure.value = true
        })
    }
}