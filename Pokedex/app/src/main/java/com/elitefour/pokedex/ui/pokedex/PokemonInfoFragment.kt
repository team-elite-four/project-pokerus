package com.elitefour.pokedex.ui.pokedex

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.elitefour.pokedex.R


/**
 * A simple [Fragment] subclass.
 * Use the [PokemonInfoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PokemonInfoFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pokemon_info, container, false)
    }

    companion object {
        val TAG: String = PokemonInfoFragment::class.java.simpleName

        fun getInstance(): PokemonInfoFragment {
            return PokemonInfoFragment()
        }
    }
}
