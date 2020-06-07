package com.elitefour.pokedex.ui.pokedex.pokepager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer

import com.elitefour.pokedex.R
import com.elitefour.pokedex.model.PokemonFullInfo
import com.elitefour.pokedex.ui.pokedex.PokedexViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_pokemon_info.*


/**
 * A simple [Fragment] subclass.
 * Use the [PokemonInfoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PokemonInfoFragment : Fragment() {

    private val pokedexVM: PokedexViewModel by activityViewModels()
    private lateinit var pokemonFullInfo: PokemonFullInfo
    private var url: String? = null

    companion object {
        val TAG: String = PokemonInfoFragment::class.java.simpleName
        const val POKEMON_URL_BUNDLE_KEY = "pokemon_key"
        fun getInstance(): PokemonInfoFragment {
            return PokemonInfoFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pokemon_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments.let { url ->
            this.url = url?.getString(POKEMON_URL_BUNDLE_KEY)
        }
        pokedexVM.pokedexFullInfoSuccess.observe(viewLifecycleOwner,  Observer { success ->
            if (success) {
                url?.let { url ->
                    pokedexVM.getPokemonFullInfo(url)?.let{
                        pokemonFullInfo = it
                        updateUI()
                    }
                }
            }
        })
        url?.let { pokedexVM.initializePokemonFullInfo(it) }
    }

    // We need to decide on what information we include
    private fun updateUI() {
        Picasso.get().load(pokemonFullInfo.imageURL).into(pokeImage)
    }
}
