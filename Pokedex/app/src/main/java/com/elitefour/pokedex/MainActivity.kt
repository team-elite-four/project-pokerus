package com.elitefour.pokedex

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.elitefour.pokedex.interfaces.OnClickListenerExtension
import com.elitefour.pokedex.interfaces.OnPokedexReadyListener
import com.elitefour.pokedex.model.Item
import com.elitefour.pokedex.model.Move
import com.elitefour.pokedex.model.Pokemon
import com.elitefour.pokedex.ui.itemlist.ItemListFragment
import com.elitefour.pokedex.ui.pokedex.PokedexFragment
import com.elitefour.pokedex.ui.pokedex.PokedexViewModel
import com.elitefour.pokedex.ui.pokedex.PokemonInfoFragment
import com.elitefour.pokedex.ui.setting.SettingFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnClickListenerExtension {

    companion object {
        val TAG = "elite"
    }

    private val pokedexVM: PokedexViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Hide action bar for visual enhancement
        this.supportActionBar?.hide()
        // Connect view model for the pokedex fragment
        val app = PokedexApp.getApp(this)
        pokedexVM.init(app.pokedexManager)
        // Expand search view for easier interaction
        search_view.setOnClickListener {
            search_view.onActionViewExpanded()
        }
        // We still decide not to use navigation controller for now
        // because we cannot fully utilize its mechanics to achieve our expected behavior.
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
//        val navController = findNavController(R.id.nav_host_fragment)
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        val appBarConfiguration = AppBarConfiguration(setOf(
//            R.id.navigation_pokedex, R.id.navigation_item_list, R.id.navigation_setting))
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)

        // We overwrite this listener for navView to manually implement the behavior of tab switching.
        navView.setOnNavigationItemSelectedListener { menuItem ->
            var selectedFragment: Fragment = PokedexFragment() // default
            when (menuItem.itemId) {
                R.id.navigation_pokedex -> selectedFragment = PokedexFragment()
                R.id.navigation_item_list -> selectedFragment = ItemListFragment()
                R.id.navigation_setting -> selectedFragment = SettingFragment()
            }
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.nav_host_fragment, selectedFragment, PokemonInfoFragment.TAG)
                .addToBackStack(PokemonInfoFragment.TAG)
                .commit()
            return@setOnNavigationItemSelectedListener true
        }
        // To achieve intuitive interaction
        navView.setOnNavigationItemReselectedListener {
            supportFragmentManager.popBackStack()
        }
    }

    override fun onPokemonClicked(pokemon: Pokemon) {
        var pokemonInfoFragment = PokemonInfoFragment.getInstance()
        //pokedexVM.setCu
        val pokemonBundle = Bundle().apply {
            putString(PokemonInfoFragment.POKEMON_URL_BUNDLE_KEY, pokemon.url)
        }
        pokemonInfoFragment.arguments = pokemonBundle
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.nav_host_fragment, pokemonInfoFragment, PokemonInfoFragment.TAG)
            .addToBackStack(PokemonInfoFragment.TAG)
            .commit()
        Log.i(TAG, pokemon.toString())
    }

    override fun onItemClicked(item: Item) {
        TODO("Not yet implemented")
    }

    override fun onMoveClicked(move: Move) {
        TODO("Not yet implemented")
    }

}
