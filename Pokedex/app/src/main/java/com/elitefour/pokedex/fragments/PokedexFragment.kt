package com.elitefour.pokedex.fragments

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.elitefour.pokedex.MainActivity
import com.elitefour.pokedex.PokedexApp

import com.elitefour.pokedex.R
import com.elitefour.pokedex.model.Pokemon
import com.elitefour.pokedex.viewmodel.PokedexViewModel

class PokedexFragment : Fragment() {

    companion object {
        fun newInstance() = PokedexFragment()
    }

    private lateinit var viewModel: PokedexViewModel
    private lateinit var app: PokedexApp
    private lateinit var pokemonList: ArrayList<Pokemon>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.pokedex_fragment, container, false)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        app = PokedexApp.getApp(context)

        viewModel.init(app.apiManager)

        viewModel.pokemonList.observe(this,  Observer { pokemonList ->
            this.pokemonList = pokemonList


        })

        viewModel.failure.observe(this, Observer { failure ->
            if (failure) {
                Log.i(MainActivity.TAG, "We have failed")
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.updatePokemonList()
    }

}