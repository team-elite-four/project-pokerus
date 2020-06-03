package com.elitefour.pokedex

import android.app.Application
import android.content.Context
import com.elitefour.pokedex.managers.APIManager
import com.elitefour.pokedex.managers.PokedexManager

class PokedexApp: Application()  {

    companion object {
        fun getApp(context: Context): PokedexApp {
            return context.applicationContext as PokedexApp
        }
    }

    lateinit var apiManager: APIManager
    lateinit var pokedexManager: PokedexManager

    override fun onCreate() {
        super.onCreate()

        apiManager = APIManager(this)
        pokedexManager = PokedexManager(this)
    }

}