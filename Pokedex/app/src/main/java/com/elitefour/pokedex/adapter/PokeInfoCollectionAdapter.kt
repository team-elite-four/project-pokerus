package com.elitefour.pokedex.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.elitefour.pokedex.ui.pokedex.pokemoninfopager.PokemonDescriptionFragment
import com.elitefour.pokedex.ui.pokedex.pokemoninfopager.PokemonInfoFragment
import com.elitefour.pokedex.ui.pokedex.pokemoninfopager.PokemonMoveListFragment

class PokeInfoCollectionAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    // Return a NEW fragment instance in createFragment(int) based on the index
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            GENERAL_INDEX -> PokemonInfoFragment.getInstance()
            MOVES_INDEX -> PokemonMoveListFragment.getInstance()
            else -> PokemonDescriptionFragment.getInstance()
        }
    }

    companion object {
        private const val GENERAL_INDEX = 0
        private const val MOVES_INDEX = 1
    }
}