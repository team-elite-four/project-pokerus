package com.elitefour.pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.elitefour.pokedex.ui.pokedex.PokedexViewModel

class MainActivity : AppCompatActivity() {

    companion object {
        val TAG = "elite"
    }

    private lateinit var app: PokedexApp

    private val pokedexViewModel: PokedexViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}
