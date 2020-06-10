package com.elitefour.pokedex

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.elitefour.pokedex.interfaces.OnClickListenerExtension
import com.elitefour.pokedex.model.Item
import com.elitefour.pokedex.model.Move
import com.elitefour.pokedex.model.MoveFullInfo
import com.elitefour.pokedex.model.Pokemon
import com.elitefour.pokedex.ui.favorites.FavoritesViewModel
import com.elitefour.pokedex.ui.favorites.TeamListFragment
import com.elitefour.pokedex.ui.itemlist.ItemInfoFragment
import com.elitefour.pokedex.ui.itemlist.ItemListFragment
import com.elitefour.pokedex.ui.movelist.MoveListFragment
import com.elitefour.pokedex.ui.movelist.MoveListViewModel
import com.elitefour.pokedex.ui.pokedex.PokedexFragment
import com.elitefour.pokedex.ui.pokedex.PokedexInfoViewPagerFragment
import com.elitefour.pokedex.ui.pokedex.PokedexViewModel
import com.elitefour.pokedex.ui.pokedex.pokemoninfopager.PokemonInfoFragment
import com.elitefour.pokedex.ui.setting.SettingFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import java.io.File
import java.io.IOException


class MainActivity : AppCompatActivity(), OnClickListenerExtension {

    companion object {
        val TAG = "elite"
        val PREF = "USER_PREF"
    }

    private val pokedexVM: PokedexViewModel by viewModels()
    private val movelistVM: MoveListViewModel by viewModels()
    private  val favoritesVM: FavoritesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Hide action bar for visual enhancement
        this.supportActionBar?.hide()
        // Connect view model for the pokedex fragment
        val app = PokedexApp.getApp(this)
        pokedexVM.init(app.pokedexManager)
        movelistVM.init(app.moveListManager)
        favoritesVM.init(app.favoritesManager)

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
                R.id.navigation_move_list -> selectedFragment = MoveListFragment()
                R.id.navigation_item_list -> selectedFragment = ItemListFragment()
                R.id.navigation_favorite-> selectedFragment = TeamListFragment()
                R.id.navigation_setting -> selectedFragment = SettingFragment()
            }
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.nav_host_fragment, selectedFragment, selectedFragment.tag)
                .commit()
            return@setOnNavigationItemSelectedListener true
        }
        // To achieve intuitive interaction
        navView.setOnNavigationItemReselectedListener {
            supportFragmentManager.popBackStack()
        }

        //val obj = getGsonFromAsset("ugh/5.json")
        //Log.i("YES", obj.toString())
    }

    private fun getGsonFromAsset(fileName: String): MoveFullInfo {
        var jsonString: String = ""
        try {
            jsonString = this.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
        }
        val gson = Gson()
        return gson.fromJson(jsonString, MoveFullInfo::class.java)
    }

    override fun onPokemonClicked(pokemon: Pokemon) {
        var pokedexInfoViewPagerFragment = PokedexInfoViewPagerFragment()
        pokedexVM.setCurrentPokemon(pokemon)
//        val pokemonBundle = Bundle().apply {
//            putString(PokemonInfoFragment.POKEMON_URL_BUNDLE_KEY, pokemon.url)
//        }
//        pokedexInfoViewPagerFragment.arguments = pokemonBundle
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.nav_host_fragment, pokedexInfoViewPagerFragment, PokemonInfoFragment.TAG)
            .addToBackStack(PokemonInfoFragment.TAG)
            .commit()
    }

    override fun onItemClicked(item: Item) {
        var itemInfoFragment = ItemInfoFragment.getInstance(item.url)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.nav_host_fragment, itemInfoFragment, ItemInfoFragment.TAG)
            .addToBackStack(ItemInfoFragment.TAG)
            .commit()
    }

    override fun onMoveClicked(moveFullInfo: MoveFullInfo) {
        Log.i("Elite", "move clicked! $moveFullInfo")
    }

}
