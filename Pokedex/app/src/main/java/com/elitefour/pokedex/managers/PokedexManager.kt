package com.elitefour.pokedex.managers

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.elitefour.pokedex.PokedexApp
import com.elitefour.pokedex.interfaces.OnPokedexReadyListener
import com.elitefour.pokedex.model.*

class PokedexManager(context: Context) {

    var dataReady: Boolean = false
    private var pokemonList: ArrayList<Pokemon>
    private var pokemonFullInfoList: ArrayList<PokemonFullInfo>
    private var apiManager: APIManager
    var onPokedexReadyListener: OnPokedexReadyListener? = null

    init {
        pokemonList = ArrayList()
        pokemonFullInfoList = ArrayList()
        apiManager = (context.applicationContext as PokedexApp).apiManager
        initializePokemonList()
    }


//    fun getPokemonList(): ArrayList<Pokemon> {
//        return pokemonList
//    }
//
    private fun initializePokemonList() {
        apiManager.fetchPokemonList ({ resultList ->
            pokemonList = resultList as ArrayList<Pokemon>
            pokemonList.forEachIndexed {index:Int, pokemon:Pokemon ->

                // Set image url
                pokemonList[index] = pokemon.copy(imageURL = apiManager.fetchPokemonImageURL(index+1))

                apiManager.fetchPokemonFullInfo (pokemon.url, { pokemonFullInfo ->
                    pokemonFullInfoList.add(pokemonFullInfo)

                    // Set up Types
                    pokemonList[index] = pokemonList[index].copy(types = pokemonFullInfo.types)

                    // Notify changes
                    this.onPokedexReadyListener?.ready()

                    if (pokemonFullInfoList.size == pokemonList.size) {
                        Log.i("Manager", "HOOOOOOOOOORAAAAAAAAAAAAAAAAYYYYYYYYY we downloaded everything!!!!")
                    }

                }, {
                    Log.i("Manager", "Pokemon List Fetch error in manager")
                })

            }
            dataReady = true
            this.onPokedexReadyListener?.ready()
        }, {
            Log.i("Manager", "Pokemon List Fetch error in manager")
        })
    }

    fun getPokemonList(): List<Pokemon> {
        return pokemonList
    }

}