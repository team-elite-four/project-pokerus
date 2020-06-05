package com.elitefour.pokedex.managers

import android.content.Context
import android.util.Log
import com.elitefour.pokedex.PokedexApp
import com.elitefour.pokedex.interfaces.OnPokedexReadyListener
import com.elitefour.pokedex.model.*

class PokedexManager(context: Context) {

    var pokedexNameImageReady: Boolean = false
    var pokedexTypeReady: Boolean = false
    var onPokedexReadyListener: OnPokedexReadyListener? = null


    private var pokemonTypeMap: MutableMap<Type, List<PokemonSlot>> = mutableMapOf()
    private var pokemonList: ArrayList<Pokemon> = ArrayList()
    private var pokemonFullInfoList: ArrayList<PokemonFullInfo> = ArrayList()
    private var apiManager: APIManager = (context.applicationContext as PokedexApp).apiManager

    init {
        initializePokedexNameImage()
    }

    private fun initializePokedexNameImage() {
        apiManager.fetchPokemonList ({ resultList ->
            pokemonList = resultList as ArrayList<Pokemon>
            pokemonList.forEachIndexed {index:Int, pokemon:Pokemon ->
                // Make image url
                pokemonList[index] = pokemon.copy(imageURL = apiManager.fetchPokemonImageURL(index+1))
            }
            // Once we have the list, initialize all types
            initializePokedexType()
            pokedexNameImageReady = true
            this.onPokedexReadyListener?.pokedexNameImageReady()
        }, {
            Log.i("Manager", "Pokemon List Fetch error in manager")
        })
    }

    /**
     *
     * Notice here in the pokemon slots, image urls and types are null
     * So never call on these 2 variables
     *
     */
    private fun initializePokedexType() {
        val numOfTypes = apiManager.getNumberOfTypes()
        for (id in 1..numOfTypes) {
            apiManager.fetchTypeInfo(id, { TypeInfo ->
                val type = Type(TypeInfo.name, apiManager.getTypeUrl(id))
                pokemonTypeMap[type] = TypeInfo.pokemon // Value is a list of PokemonSlots
                assignPokemonTypes(type, TypeInfo.pokemon)
                if (id == numOfTypes) {
                    pokedexTypeReady = true
                    this.onPokedexReadyListener?.pokedexTypeReady()
                }
            }, {
                Log.i("Manager", "Pokemon Type Fetch error in manager")
            })
        }
    }

    /**
     * For each pokemon slot in a certain type, assign it to the pokemon list
     */
    private fun assignPokemonTypes(type: Type, pokemonSlotList: List<PokemonSlot>) {
        for (pokemonSlot in pokemonSlotList) {
            val pokemonIndex = getIDFromPokemonURL(pokemonSlot.pokemon.url) - 1
            if (pokemonIndex < 0) continue
            var types: MutableList<TypeSlot> = mutableListOf()
            val typeSlot = TypeSlot(slot = pokemonSlot.slot, type = type)
            // If at least one type has been added
            if (pokemonList[pokemonIndex].types != null) {
                types = pokemonList[pokemonIndex].types as MutableList<TypeSlot>
                if (typeSlot.slot == 2) {
                    types.add(typeSlot)
                } else {
                    types[0] = typeSlot
                }
            } else { // first time making a list of type slot
                // make a dummy type at the first slot
                if (typeSlot.slot == 2) {
                    types.add(TypeSlot(slot = 1, type = type))
                }
                types.add(typeSlot)
            }
            pokemonList[pokemonIndex] = pokemonList[pokemonIndex].copy(types = types)
        }
    }

    /**
     * Any pokemon ID beyond the limit will be neglected, as a result of returning -1
     * @return The ID of the pokemon in the given url for that pokemon or -1
     */
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