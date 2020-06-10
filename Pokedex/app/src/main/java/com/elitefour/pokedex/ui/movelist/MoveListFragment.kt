package com.elitefour.pokedex.ui.movelist

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.activityViewModels

import com.elitefour.pokedex.R
import com.elitefour.pokedex.adapter.MoveListAdapter
import com.elitefour.pokedex.interfaces.OnClickListenerExtension
import com.elitefour.pokedex.model.MoveFullInfo
import kotlinx.android.synthetic.main.fragment_move_list.*

class MoveListFragment : Fragment() {

    private lateinit var moveListAdapter: MoveListAdapter
    private lateinit var inputMethodManager: InputMethodManager
    private val moveListVM: MoveListViewModel by activityViewModels()
    private var mainActivityListener: OnClickListenerExtension? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnClickListenerExtension) {
            mainActivityListener = context
        }
        inputMethodManager = getSystemService(context, InputMethodManager::class.java)!!
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

        rvMove.setOnTouchListener { v, event ->
            inputMethodManager.hideSoftInputFromWindow(move_search_view.windowToken, 0)
        }
        move_search_view.setOnClickListener {
            move_search_view.onActionViewExpanded()
        }
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
        initAdapter()
    }

    private fun initAdapter() {
        moveListAdapter = MoveListAdapter(moveListVM.getFullMoveList())
        rvMove.adapter = moveListAdapter
        moveListAdapter.onMoveClickListener = { moveFullInfo: MoveFullInfo ->
            move_search_view.clearFocus()
            mainActivityListener?.onMoveClicked(moveFullInfo)
        }
    }

    private fun updateAdapter(moveList: ArrayList<MoveFullInfo>) {
        moveListAdapter.change(moveList)
    }
}
