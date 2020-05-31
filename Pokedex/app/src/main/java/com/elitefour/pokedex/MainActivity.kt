package com.elitefour.pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.elitefour.pokedex.managers.APIManager
import com.elitefour.pokedex.viewmodel.PokedexViewModel

class MainActivity : AppCompatActivity() {

    companion object {
        val TAG = "elite"
    }

    private lateinit var app: PokedexApp

    private val pokedexViewModel: PokedexViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        app = (application as PokedexApp)

        pokedexViewModel.init(app.apiManager)

        pokedexViewModel.pokemonList.observe(this,  Observer { pokemonList ->
            Log.i( TAG, pokemonList.toString())
        })

        pokedexViewModel.failure.observe(this, Observer { failure ->
            if (failure) {
                Log.i(TAG, "We have failed")
            }

        })

        pokedexViewModel.getPokemonList()
    }
}
