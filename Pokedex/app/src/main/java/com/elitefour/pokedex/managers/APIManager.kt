package com.elitefour.pokedex.managers

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.elitefour.pokedex.model.Pokemon
import com.elitefour.pokedex.model.PokemonCollection
import com.google.gson.Gson

class APIManager(context: Context) {

    private val queue: RequestQueue = Volley.newRequestQueue(context)
    private val POKEAPI_URL = "https://pokeapi.co/api/v2/"
    private val POKEMON = "pokemon/"

    fun fetchPokemonList(onDataReady: (List<Pokemon>) -> Unit, onError: (() -> Unit)? = null) {
        val request = StringRequest(Request.Method.GET, POKEAPI_URL+POKEMON,
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



}