package com.elitefour.pokedex

import android.app.Application
import android.content.Context
import com.elitefour.pokedex.managers.*

class PokedexApp: Application()  {

    companion object {
        fun getApp(context: Context): PokedexApp {
            return context.applicationContext as PokedexApp
        }
    }

    lateinit var apiManager: APIManager
    lateinit var pokedexManager: PokedexManager
    lateinit var moveListManager: MoveListManager
    lateinit var itemListManager: ItemListManager
    lateinit var favoritesManager: FavoritesManager
    lateinit var preferenceManager: PreferenceManager

    override fun onCreate() {
        super.onCreate()

        apiManager = APIManager(this)
        pokedexManager = PokedexManager(this)
        moveListManager = MoveListManager(this)
        itemListManager = ItemListManager(this)
        favoritesManager = FavoritesManager(this)
        preferenceManager = PreferenceManager(this)
    }

}