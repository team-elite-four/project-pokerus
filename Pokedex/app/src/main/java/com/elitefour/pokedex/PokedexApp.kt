package com.elitefour.pokedex

import android.app.Application
import android.content.Context
import com.elitefour.pokedex.managers.APIManager
import com.elitefour.pokedex.managers.ItemListManager
import com.elitefour.pokedex.managers.MoveListManager
import com.elitefour.pokedex.managers.PokedexManager

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

    override fun onCreate() {
        super.onCreate()

        apiManager = APIManager(this)
        pokedexManager = PokedexManager(this)
        moveListManager = MoveListManager(this)
        itemListManager = ItemListManager(this)
    }

}