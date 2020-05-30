package com.elitefour.pokedex

import android.app.Application
import com.elitefour.pokedex.managers.APIManager

class PokedexApp: Application()  {

    lateinit var apiManager: APIManager

    override fun onCreate() {
        super.onCreate()

        apiManager = APIManager(this)
    }
}