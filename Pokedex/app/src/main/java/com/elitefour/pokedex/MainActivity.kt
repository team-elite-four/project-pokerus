package com.elitefour.pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.elitefour.pokedex.interfaces.OnClickListenerExtention
import com.elitefour.pokedex.model.Pokemon
import com.elitefour.pokedex.ui.pokedex.PokedexFragment
import com.elitefour.pokedex.ui.pokedex.PokedexViewModel

class MainActivity : AppCompatActivity(), OnClickListenerExtention{

    companion object {
        val TAG = "elite"
    }

    private lateinit var app: PokedexApp
    private lateinit var pokedexFragment: PokedexFragment

    private val pokedexViewModel: PokedexViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pokedexFragment = PokedexFragment.newInstance()

        supportFragmentManager
            .beginTransaction()
            .add(R.id.nav_host_fragment, pokedexFragment, PokedexFragment.TAG)
            .commit()
    }

    override fun onPokemonClicked(pokemon: Pokemon) {
        Log.i(TAG, pokemon.name)
    }
}
