package com.elitefour.pokedex

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.elitefour.pokedex.interfaces.OnClickListenerExtension
import com.elitefour.pokedex.model.Pokemon
import com.elitefour.pokedex.ui.pokedex.PokedexFragment
import com.elitefour.pokedex.ui.pokedex.PokedexViewModel
import com.elitefour.pokedex.ui.pokedex.PokemonInfoFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnClickListenerExtension{

    companion object {
        val TAG = "elite"
    }


    private val pokedexViewModel: PokedexViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        pokedexFragment = PokedexFragment.newInstance()
//
//        supportFragmentManager
//            .beginTransaction()
//            .add(R.id.nav_host_fragment, pokedexFragment, PokedexFragment.TAG)
//            .commit()

        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_pokedex, R.id.navigation_item_list, R.id.navigation_setting))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        search_view.setOnClickListener {
            search_view.onActionViewExpanded()
        }
    }

    override fun onPokemonClicked(pokemon: Pokemon) {
        var pokemonInfoFragment = PokemonInfoFragment.getInstance()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.nav_host_fragment, pokemonInfoFragment, PokemonInfoFragment.TAG)
            .addToBackStack(PokemonInfoFragment.TAG)
            .commit()
        Log.i(TAG, pokemon.name)
    }
}
