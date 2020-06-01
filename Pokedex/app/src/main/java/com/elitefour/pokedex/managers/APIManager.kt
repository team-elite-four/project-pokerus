package com.elitefour.pokedex.managers

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.elitefour.pokedex.model.Pokemon
import com.elitefour.pokedex.model.PokemonCollection
import com.elitefour.pokedex.model.PokemonInfo
import com.google.gson.Gson

class APIManager(context: Context) {

    private val queue: RequestQueue = Volley.newRequestQueue(context)

    companion object {
        private const val POKEAPI_URL = "https://pokeapi.co/api/v2/"
        private const val POKEIMAGE_URL = "https://pokeres.bastionbot.org/images/pokemon/"
        private const val POKEMON_LIST = "pokemon?limit="
        private const val NUM_OF_POKEMONS = 807 // Excluding pokemon from sword and shield
    }

    /**
     * Takes a promise function and a error handling function
     * returns the results list from the entire pokemon collection in the promise
     * The results list contains the name of each pokemon and its info url
     */
    fun fetchPokemonList(onDataReady: (List<Pokemon>) -> Unit, onError: (() -> Unit)? = null) {
        val url = POKEAPI_URL + POKEMON_LIST + NUM_OF_POKEMONS
        val request = StringRequest(Request.Method.GET, url,
            { response ->
                // Success
                val gson = Gson()
                val collection = gson.fromJson(response, PokemonCollection::class.java )
                onDataReady(collection.results)
            }, {
                onError?.invoke()
            }
        )
        queue.add(request)
    }

    /**
     * Takes an url, a promise function and a error handling function
     * returns the information of a specific pokemon in the promise
     * @param url The url requesting the detail of a pokemon.
     */
    fun fetchPokemonInfo(url: String, onDataReady: (PokemonInfo) -> Unit, onError: (() -> Unit)? = null) {
        val request = StringRequest(Request.Method.GET, url,
            { response ->
                // Success
                val gson = Gson()
                val pokemonInfo = gson.fromJson(response, PokemonInfo::class.java )
                onDataReady(pokemonInfo)
            }, {
                onError?.invoke()
            }
        )
        queue.add(request)
    }

    /**
     * Takes an url, a promise function and a error handling function
     * returns the information of a specific pokemon in the promise
     * @param id The url requesting the detail of a pokemon.
     */
    fun fetchPokemonImageURL(id: Int): String {
        return "${POKEIMAGE_URL+id}.png"
    }


}