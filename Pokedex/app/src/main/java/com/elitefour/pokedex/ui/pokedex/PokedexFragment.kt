package com.elitefour.pokedex.ui.pokedex

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
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

    private lateinit var inputMethodManager: InputMethodManager


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
        inputMethodManager = getSystemService(context, InputMethodManager::class.java)!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pokedex_search_view.setOnClickListener {
            pokedex_search_view.onActionViewExpanded()
        }
        rvPokemon.setOnTouchListener { v, event ->
            inputMethodManager.hideSoftInputFromWindow(pokedex_search_view.windowToken, 0)
        }
        pokedex_search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                pokedex_search_view.clearFocus()
                pokedex_search_view.setQuery("", false)
                if (query != null) {
                    updateAdapter(pokedexVM.getQueriedPokemonList(query))
                }
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    updateAdapter(pokedexVM.getQueriedPokemonList(newText))
                }
                return true
            }
        })

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
    }

    private fun initAdapter() {
        pokedexLoading.visibility = View.GONE
        rvPokemon.visibility = View.VISIBLE
        pokemonListAdapter = PokemonListAdapter(pokedexVM.getPokemonList())
        rvPokemon.adapter = pokemonListAdapter
        pokemonListAdapter.onPokemonClickListener = { pokemon: Pokemon ->
            mainActivityListener?.onPokemonClicked(pokemon)
        }
    }

    private fun updateAdapter(pokemonList: ArrayList<Pokemon>) {
        pokemonListAdapter = PokemonListAdapter(pokemonList)
        rvPokemon.adapter = pokemonListAdapter
        pokemonListAdapter.onPokemonClickListener = { pokemon: Pokemon ->
            mainActivityListener?.onPokemonClicked(pokemon)
        }
    }
}
