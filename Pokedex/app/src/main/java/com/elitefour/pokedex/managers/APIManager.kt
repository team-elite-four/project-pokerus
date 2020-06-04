package com.elitefour.pokedex.managers

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.elitefour.pokedex.model.*
import com.google.gson.Gson
import java.lang.Exception

class APIManager(context: Context) {

    private val queue: RequestQueue = Volley.newRequestQueue(context)

    companion object {
        private const val POKEAPI_URL = "https://pokeapi.co/api/v2/"
        private const val POKEIMAGE_URL = "https://pokeres.bastionbot.org/images/pokemon/"
        private const val POKEMON_LIST = "pokemon?limit="
        private const val ITEM_LIST = "item?limit="
        private const val TYPE_LIST = "type/"
        private const val NUM_OF_POKEMONS = 807 // Excluding pokemon from sword and shield
        private const val NUM_OF_ITEMS = 807 // All items
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
                try {
                    val pokemonInfo = gson.fromJson(response, PokemonFullInfo::class.java )
                    onDataReady(pokemonInfo)
                } catch (e: Exception) {
                    Log.e("elite", e.toString() + url)
                }
            }, {
                onError?.invoke()
            }
        )
        queue.add(request)
    }

    /**
     * Takes the id of a pokemon to return the pokemon's image URL
     * @param id The url requesting the detail of a pokemon.
     * @return the string URL of the pokemon image
     */
    fun fetchPokemonImageURL(id: Int): String {
        return "${POKEIMAGE_URL+id}.png"
    }

    /**
     * Takes the id of a type to return the URL for that specific type
     * @param id The url requesting the detail of a type.
     * @return the string URL of the type
     */
    fun fetchTypeURL(id: Int): String {
        return "${POKEAPI_URL+ TYPE_LIST+id}"
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

    fun fetchTypeList(onDataReady: (List<Type>) -> Unit, onError: (() -> Unit)? = null) {
        val url = POKEAPI_URL + TYPE_LIST
        val request = StringRequest(Request.Method.GET, url,
            { response ->
                // Success
                val gson = Gson()
                val collection = gson.fromJson(response, TypeCollection::class.java)
                onDataReady(collection.results)
            }, {
                onError?.invoke()
            }
        )
        queue.add(request)
    }

    fun fetchTypeInfo(url: String, onDataReady: (TypeInfo) -> Unit, onError: (() -> Unit)? = null) {
        val request = StringRequest(Request.Method.GET, url,
            { response ->
                // Success
                val gson = Gson()
                val typeInfo = gson.fromJson(response, TypeInfo::class.java )
                onDataReady(typeInfo)
            }, {
                onError?.invoke()
            }
        )
        queue.add(request)
    }
}