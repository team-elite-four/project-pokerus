package com.elitefour.pokedex.ui.movelist

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.activityViewModels

import com.elitefour.pokedex.R
import com.elitefour.pokedex.adapter.MoveListAdapter
import com.elitefour.pokedex.interfaces.OnClickListenerExtension
import com.elitefour.pokedex.model.MoveFullInfo
import kotlinx.android.synthetic.main.fragment_move_list.*
import kotlinx.android.synthetic.main.fragment_pokedex.*

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

        move_search_view.setOnClickListener {
            move_search_view.onActionViewExpanded()
        }
        initAdapter()

        move_search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                move_search_view.clearFocus()
                move_search_view.setQuery("", false)
                if (query != null) {
                    updateAdapter(moveListVM.getQueriedMoveList(query))
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    updateAdapter(moveListVM.getQueriedMoveList(newText))
                }
                return true
            }
        })
    }

    private fun initAdapter() {
        moveListAdapter = MoveListAdapter(moveListVM.getFullMoveList())
        rvMove.adapter = moveListAdapter
        moveListAdapter.onMoveClickListener = { moveFullInfo: MoveFullInfo ->
            pokedex_search_view.clearFocus()
            mainActivityListener?.onMoveClicked(moveFullInfo)
        }
    }

    private fun updateAdapter(moveFullInfo: ArrayList<MoveFullInfo>) {
        moveListAdapter = MoveListAdapter(moveFullInfo)
        rvMove.adapter = moveListAdapter
        moveListAdapter.onMoveClickListener = { moveFullInfo: MoveFullInfo ->
            pokedex_search_view.clearFocus()
            mainActivityListener?.onMoveClicked(moveFullInfo)
        }
    }

    companion object {
        val TAG = MoveListFragment::class.simpleName
    }
}
