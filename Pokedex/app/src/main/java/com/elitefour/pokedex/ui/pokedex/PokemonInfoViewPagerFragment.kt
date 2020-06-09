package com.elitefour.pokedex.ui.pokedex

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

import com.elitefour.pokedex.R
import com.elitefour.pokedex.adapter.PokeInfoCollectionAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

/**
 * A simple [Fragment] subclass.
 * Use the [PokedexInfoViewPagerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PokedexInfoViewPagerFragment : Fragment() {

    private lateinit var pokeInfoCollectionAdapter: PokeInfoCollectionAdapter
    private lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pokemon_info_view_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        pokeInfoCollectionAdapter = PokeInfoCollectionAdapter(this)
        viewPager = view.findViewById(R.id.pokemon_info_pager)
        viewPager.adapter = pokeInfoCollectionAdapter
        val tabLayout = view.findViewById<TabLayout>(R.id.tab_layout)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "General"
                1 -> tab.text = "Moves"
                2 -> tab.text = "Description"
            }
        }.attach()
    }
}


