package com.elitefour.pokedex.ui.pokedex

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

import com.elitefour.pokedex.R
import com.elitefour.pokedex.ui.pokedex.pokemoninfopager.PokemonInfoFragment
import com.elitefour.pokedex.ui.pokedex.pokemoninfopager.PokemonInfoFragment.Companion.POKEMON_URL_BUNDLE_KEY
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
    private lateinit var url: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pokemon_info_view_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        arguments.let { bundle ->
            bundle?.let{ bundle1 ->
                bundle1.getString(POKEMON_URL_BUNDLE_KEY)?.let {
                    this.url = it
                }
            }
        }
        pokeInfoCollectionAdapter = PokeInfoCollectionAdapter(this, url)
        viewPager = view.findViewById(R.id.poke_info_pager)
        viewPager.adapter = pokeInfoCollectionAdapter
        val tabLayout = view.findViewById<TabLayout>(R.id.tab_layout)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = "OBJECT ${(position + 1)}"
        }.attach()
    }

    companion object {
        val TAG = PokedexInfoViewPagerFragment::class.simpleName
    }
}

class PokeInfoCollectionAdapter(fragment: Fragment, urlInit: String) : FragmentStateAdapter(fragment) {
    private val url = urlInit

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        // Return a NEW fragment instance in createFragment(int)
        val fragment = PokemonInfoFragment()
        fragment.arguments = Bundle().apply {
            putString(PokemonInfoFragment.POKEMON_URL_BUNDLE_KEY, url)
        }
        return fragment
    }
}

