package com.elitefour.pokedex.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.elitefour.pokedex.ui.pokedex.pokemoninfopager.PokemonInfoFragment

class PokeInfoCollectionAdapter(fragment: Fragment, urlInit: String) : FragmentStateAdapter(fragment) {
    private val url = urlInit

    override fun getItemCount(): Int = 3

    // Return a NEW fragment instance in createFragment(int) based on the index
    override fun createFragment(position: Int): Fragment {
        val fragment = PokemonInfoFragment.getInstance()
        fragment.arguments = Bundle().apply {
            putString(PokemonInfoFragment.POKEMON_URL_BUNDLE_KEY, url)
        }
        return fragment
    }
}