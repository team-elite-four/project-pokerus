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

    private val pokedexVM: PokedexViewModel by activityViewModels()
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
        if (context is OnClickListenerExtension) {
            mainActivityListener = context
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pokedexVM.pokedexNameImageSuccess.observe(viewLifecycleOwner,  Observer { success ->
            if (success and pokedexVM.pokedexTypeSuccess.value!!) {
                initAdapter()
            }
        })
        pokedexVM.pokedexTypeSuccess.observe(viewLifecycleOwner,  Observer { success ->
            if (success and pokedexVM.pokedexNameImageSuccess.value!!) {
                initAdapter()
            }
        })

        if (pokedexVM.pokedexNameImageSuccess.value!! and pokedexVM.pokedexTypeSuccess.value!!) {
            initAdapter()
        }
    }

    private fun initAdapter() {
        pokemonListAdapter = PokemonListAdapter(pokedexVM.getPokemonList())
        rvPokemon.adapter = pokemonListAdapter
        pokemonListAdapter.onPokemonClickListener = { pokemon: Pokemon ->
            mainActivityListener?.onPokemonClicked(pokemon)
        }
    }
}
