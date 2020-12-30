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
import com.elitefour.pokedex.model.MoveFullInfo
import com.elitefour.pokedex.model.MoveInfo
import com.elitefour.pokedex.model.PokemonFullInfo
import com.elitefour.pokedex.ui.movelist.MoveListViewModel
import com.elitefour.pokedex.ui.pokedex.PokedexViewModel
import kotlinx.android.synthetic.main.fragment_move_list.*
import kotlinx.android.synthetic.main.fragment_pokemon_move_list.*

/**
 * A simple [Fragment] subclass.
 * Use the [PokemonMoveListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PokemonMoveListFragment : Fragment() {

    private val pokedexVM: PokedexViewModel by activityViewModels()
    private val moveListVM: MoveListViewModel by activityViewModels()
    private lateinit var moveListAdapter: MoveListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pokemon_move_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pokedexVM.pokedexFullInfoSuccess.observe(viewLifecycleOwner,  Observer { success ->
            if (success) {
                val url = pokedexVM.getCurrentPokemon().url
                val pokemonFullInfo = pokedexVM.getPokemonFullInfo(url)
                if (pokemonFullInfo != null) {
                    initAdapter(pokemonFullInfo)
                }
            }
        })
    }

    private fun initAdapter(pokemonFullInfo: PokemonFullInfo) {
        moveListVM.convertMoveList(pokemonFullInfo.moves as ArrayList<MoveInfo>)
        moveListAdapter = MoveListAdapter(moveListVM.getConvertedMoveList(), pokemonFullInfo.moves)
        rvPokemonMoveList.adapter = moveListAdapter
    }

    companion object {
        fun getInstance(): PokemonMoveListFragment {
            return PokemonMoveListFragment()
        }
    }
}
