package com.elitefour.pokedex.managers

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.elitefour.pokedex.PokedexApp
import com.elitefour.pokedex.interfaces.OnPokedexReadyListener
import com.elitefour.pokedex.model.Pokemon
import com.elitefour.pokedex.model.PokemonFullInfo
import com.elitefour.pokedex.model.PokemonInfo

class PokedexManager(context: Context) {

    var dataReady: Boolean = false
    private var pokemonList: List<Pokemon>
    private var pokemonInfoList: List<PokemonInfo>
    private var pokemonFullInfoList: List<PokemonFullInfo>
    private var apiManager: APIManager
    var onPokedexReadyListener: OnPokedexReadyListener? = null

    init {
        pokemonList = emptyList()
        pokemonInfoList = emptyList()
        pokemonFullInfoList = emptyList()
        apiManager = (context.applicationContext as PokedexApp).apiManager
        initializePokemonList()
    }


    fun initializePokemonList() {
        apiManager.fetchPokemonList ({ resultList ->
            pokemonList = resultList as ArrayList<Pokemon>
            this.onPokedexReadyListener?.ready()
            dataReady = true
        }, {
            Log.i("Manager", "Pokemon List Fetch error in manager")
        })
    }

}