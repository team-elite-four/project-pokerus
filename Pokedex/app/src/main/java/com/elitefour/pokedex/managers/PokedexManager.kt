package com.elitefour.pokedex.managers

import android.content.Context
import android.util.Log
import com.elitefour.pokedex.PokedexApp
import com.elitefour.pokedex.interfaces.OnPokedexReadyListener
import com.elitefour.pokedex.model.*

class PokedexManager(context: Context) {

    var dataReady: Boolean = false
    private var pokemonList: ArrayList<Pokemon>
    private lateinit var pokemonFullInfoMap: HashMap<Int, PokemonFullInfo>
    private var apiManager: APIManager
    var onPokedexReadyListener: OnPokedexReadyListener? = null

    init {
        pokemonList = ArrayList()
        pokemonFullInfoMap = HashMap()
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

                initializePokemonInfo(pokemon, index)

                if (pokemonFullInfoMap.size == pokemonList.size) {
                    Log.i("Manager", "HOOOOOOOOOORAAAAAAAAAAAAAAAAYYYYYYYYY we downloaded everything!!!!")
                }

            }
            dataReady = true
            this.onPokedexReadyListener?.readyList()
        }, {
            Log.i("Manager", "Pokemon List Fetch error in manager")
        })
    }

    fun initializePokemonInfo(pokemon: Pokemon, index: Int) {
        apiManager.fetchPokemonFullInfo (pokemon.url, { pokemonFullInfo ->
            pokemonFullInfoMap[(index + 1)] = pokemonFullInfo
            // Set up Types commented out since Erik was working on TypeList implementation for types set up in pokedex
            pokemonList[index] = pokemonList[index].copy(types = pokemonFullInfo.types)
            // Notify changes
            this.onPokedexReadyListener?.readyList()
            this.onPokedexReadyListener?.readyInfo()
        }, {
            Log.i("Manager", "Pokemon List Fetch error in manager")
        })
    }

    fun getPokemonInfo(url: String): PokemonFullInfo? {
        val id = getIDFromPokemonURL(url)
        if (pokemonFullInfoMap.contains(id)) {

        } else {
            initializePokemonInfo(pokemonList[id - 1], id - 1)
        }
        return null
    }

    private fun getIDFromPokemonURL(url: String): Int {
        val arr = url.split("/")
        val limit = apiManager.getNumberOfPokemons()
        val id = arr[arr.lastIndex-1].toInt()
        return if (id > limit) -1 else id
    }

    fun getPokemonList(): List<Pokemon> {
        return pokemonList
    }

}