package com.elitefour.pokedex.ui.movelist

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels

import com.elitefour.pokedex.R
import com.elitefour.pokedex.adapter.MoveListAdapter
import com.elitefour.pokedex.interfaces.OnClickListenerExtension
import com.elitefour.pokedex.model.Move

/**
 * A simple [Fragment] subclass.
 * Use the [MoveListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MoveListFragment : Fragment() {

    private val moveListVM: MoveListViewModel by activityViewModels()
    private lateinit var moveListAdapter: MoveListAdapter
    private var mainActivityListener: OnClickListenerExtension? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnClickListenerExtension) {
            mainActivityListener = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_move_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun initAdapter() {

    }

    companion object {
        val TAG = MoveListFragment::class.simpleName
    }
}
