package com.elitefour.pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.elitefour.pokedex.managers.APIManager
import com.elitefour.pokedex.model.Pokemon

class MainActivity : AppCompatActivity() {

    private lateinit var apiManager: APIManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var pokemonList: List<Pokemon> = emptyList()
        apiManager = (application as PokedexApp).apiManager

        apiManager.fetchPokemonList ({ resultList ->
            pokemonList = resultList
            Log.i("erik", pokemonList.toString())
        }, {
            Toast.makeText(this, "Failed to fetch the list", Toast.LENGTH_SHORT).show()
        })
    }
}
