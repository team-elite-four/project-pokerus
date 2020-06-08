package com.elitefour.pokedex.ui.movelist

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    private val moveListVM: MoveListViewModel by viewModels()
    private lateinit var moveListAdapter: MoveListAdapter
    private lateinit var moveList: ArrayList<Move>
    private var mainActivityListener: OnClickListenerExtension? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnClickListenerExtension) {
            mainActivityListener = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        arguments.let { it ->
            moveList = it?.getParcelableArrayList<Move>(LIST_KEY) as ArrayList<Move>
            initAdapter()
        }

        if (!this::moveList.isInitialized) {
            // do fetch from vm
        }
    }

    private fun initAdapter() {

    }

    companion object {
        val TAG = MoveListFragment::class.simpleName
        val LIST_KEY = "list_key"
    }
}
