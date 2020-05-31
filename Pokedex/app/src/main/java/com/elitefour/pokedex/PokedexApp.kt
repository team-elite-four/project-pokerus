package com.elitefour.pokedex

import android.app.Application
import android.content.Context
import com.elitefour.pokedex.managers.APIManager

class PokedexApp: Application()  {

    companion object {
        fun getApp(context: Context): PokedexApp {
            return context.applicationContext as PokedexApp
        }
    }

    lateinit var apiManager: APIManager

    override fun onCreate() {
        super.onCreate()

        apiManager = APIManager(this)
    }

}