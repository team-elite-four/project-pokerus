package com.elitefour.pokedex.managers

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.elitefour.pokedex.model.*
import com.google.gson.Gson

class APIManager(context: Context) {

    private val queue: RequestQueue = Volley.newRequestQueue(context)

    companion object {
        private const val POKEAPI_URL = "https://pokeapi.co/api/v2/"
        private const val POKEIMAGE_URL = "https://pokeres.bastionbot.org/images/pokemon/"
        private const val POKEMON_LIST = "pokemon?limit="
        private const val ITEM_LIST = "item?limit="
        private const val NUM_OF_POKEMONS = 807 // 807: Excluding pokemon from sword and shield
        private const val NUM_OF_ITEMS = 954 // All items
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
                val collection = gson.fromJson(response, PokemonCollection::class.java)
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
    fun fetchPokemonFullInfo(url: String, onDataReady: (PokemonFullInfo) -> Unit, onError: (() -> Unit)? = null) {
        val request = StringRequest(Request.Method.GET, url,
            { response ->
                // Success
                val gson = Gson()
                val pokemonInfo = gson.fromJson(response, PokemonFullInfo::class.java )
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

    /**
     * Takes a promise function and a error handling function
     * returns the results list from the entire item collection in the promise
     * The results list contains the name of each item and its info url
     */
    fun fetchItemList(onDataReady: (List<Item>) -> Unit, onError: (() -> Unit)? = null) {
        val url = POKEAPI_URL + ITEM_LIST + NUM_OF_ITEMS
        val request = StringRequest(Request.Method.GET, url,
            { response ->
                // Success
                val gson = Gson()
                val collection = gson.fromJson(response, ItemCollection::class.java )
                onDataReady(collection.results)
            }, {
                onError?.invoke()
            }
        )
        queue.add(request)
    }

    /**
     * Takes an url, a promise function and a error handling function
     * returns the information of a specific item in the promise
     * @param url The url requesting the detail of an item.
     */
    fun fetchItemInfo(url: String, onDataReady: (ItemInfo) -> Unit, onError: (() -> Unit)? = null) {
        val request = StringRequest(Request.Method.GET, url,
            { response ->
                // Success
                val gson = Gson()
                val itemInfo = gson.fromJson(response, ItemInfo::class.java )
                onDataReady(itemInfo)
            }, {
                onError?.invoke()
            }
        )
        queue.add(request)
    }
}