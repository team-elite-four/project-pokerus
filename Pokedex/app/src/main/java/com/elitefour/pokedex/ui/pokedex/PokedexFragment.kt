package com.elitefour.pokedex.ui.pokedex

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.elitefour.pokedex.MainActivity
import com.elitefour.pokedex.PokedexApp

import com.elitefour.pokedex.R
import com.elitefour.pokedex.adapter.PokemonListAdapter
import com.elitefour.pokedex.interfaces.OnClickListenerExtension
import com.elitefour.pokedex.model.Pokemon
import kotlinx.android.synthetic.main.fragment_pokedex.*

class PokedexFragment : Fragment() {

    companion object {
        fun newInstance() = PokedexFragment()
        val TAG = PokedexFragment::class.simpleName
    }

    private val pokedexViewModel: PokedexViewModel by activityViewModels()
    private lateinit var pokemonList: ArrayList<Pokemon>
    private lateinit var pokemonListAdapter: PokemonListAdapter
    private var mainActivityListener: OnClickListenerExtension? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pokedex, container, false)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        val app = PokedexApp.getApp(context)


        if (context is OnClickListenerExtension) {
            mainActivityListener = context
        }

        pokedexViewModel.init(app.apiManager)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        pokedexViewModel.pokemonList.observe(viewLifecycleOwner,  Observer { pokemonList ->
            this.pokemonList = pokemonList
            initAdapter()

        })

//        pokedexViewModel.failure.observe(viewLifecycleOwner, Observer { failure ->
//            if (failure) {
//                Log.i(MainActivity.TAG, "We have failed")
//            }
//        })

        pokedexViewModel.updatePokemonList()
    }

    private fun initAdapter() {
        pokemonListAdapter = PokemonListAdapter(pokemonList, pokedexViewModel)
        rvPokemon.adapter = pokemonListAdapter
        pokemonListAdapter.onPokemonClickListener = { pokemon: Pokemon ->
            mainActivityListener?.onPokemonClicked(pokemon)
        }
    }
}
