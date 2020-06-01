package com.elitefour.pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.elitefour.pokedex.viewmodel.PokedexViewModel

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
