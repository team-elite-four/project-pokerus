package com.elitefour.pokedex.ui.favorites

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels

import com.elitefour.pokedex.R
import com.elitefour.pokedex.adapter.FavoritesAdapter
import com.elitefour.pokedex.adapter.PokemonListAdapter
import com.elitefour.pokedex.interfaces.OnClickListenerExtension
import com.elitefour.pokedex.model.Pokemon
import kotlinx.android.synthetic.main.fragment_team_list.*

class TeamListFragment : Fragment() {


    private val favoritesVM: FavoritesViewModel by activityViewModels()
    private lateinit var favoritesAdapter: FavoritesAdapter
    private var mainActivityListener: OnClickListenerExtension? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team_list, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnClickListenerExtension) {
            mainActivityListener = context
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
    }

    private fun initAdapter() {
        Log.i("VM", "hello")
        Log.i("VM", favoritesVM.getFavorites().toString())
        favoritesAdapter = FavoritesAdapter(favoritesVM.getFavorites())
        rvFavorites.adapter = favoritesAdapter
        favoritesAdapter.onPokemonClickListener = { pokemon: Pokemon ->
            mainActivityListener?.onPokemonClicked(pokemon)
        }
    }

    companion object {

    }
}
