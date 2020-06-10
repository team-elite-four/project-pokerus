package com.elitefour.pokedex.ui.movelist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels

import com.elitefour.pokedex.R
import com.elitefour.pokedex.ui.pokedex.PokedexFragment
import kotlinx.android.synthetic.main.fragment_move_info.*


class MoveInfoFragment : Fragment() {

    private val moveListVM: MoveListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_move_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateUI()
    }
    private fun updateUI() {
        val currentMove = moveListVM.getCurrentMove()
        tvMoveName.text = currentMove.name
        tvEffectText.text = currentMove.effect_entries[0].effect
    }

    companion object {
        fun newInstance() = PokedexFragment()
        val TAG = PokedexFragment::class.simpleName
    }

}
