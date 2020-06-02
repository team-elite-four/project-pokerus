package com.elitefour.pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.elitefour.pokedex.ui.pokedex.PokedexFragment
import com.elitefour.pokedex.ui.pokedex.PokedexViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    companion object {
        val TAG = "elite"
    }

    private lateinit var app: PokedexApp
    private lateinit var pokedexFragment: PokedexFragment

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

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_pokedex, R.id.navigation_item_list, R.id.navigation_setting))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}
