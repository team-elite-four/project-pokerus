package com.elitefour.pokedex.ui.pokedex.pokemoninfopager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer

import com.elitefour.pokedex.R
import com.elitefour.pokedex.adapter.MoveListAdapter
import com.elitefour.pokedex.model.PokemonFullInfo
import com.elitefour.pokedex.ui.pokedex.PokedexViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_pokemon_description.*
import kotlinx.android.synthetic.main.fragment_pokemon_info.*

/**
 * A simple [Fragment] subclass.
 */
class PokemonDescriptionFragment : Fragment() {

    private val pokedexVM: PokedexViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pokemon_description, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pokedexVM.pokedexFullInfoSuccess.observe(viewLifecycleOwner,  Observer { success ->
            if (success) {
                val url = pokedexVM.getCurrentPokemon().url
                val pokemonFullInfo = pokedexVM.getPokemonFullInfo(url)
                if (pokemonFullInfo != null) {
                    initUI(pokemonFullInfo)
                }
            }
        })
    }

    private fun initUI(pokemonFullInfo: PokemonFullInfo) {
        Picasso.get().load(pokemonFullInfo.imageURL).into(pokemonDescriptionImage)
        pokemonDescriptionName.text = pokemonFullInfo.name.capitalize()
        pokemonDescriptionFlavorText.text = "Information not available right now"
    }

    companion object {
        val TAG: String = PokemonInfoFragment::class.java.simpleName
        fun getInstance(): PokemonDescriptionFragment {
            return PokemonDescriptionFragment()
        }
    }
}
