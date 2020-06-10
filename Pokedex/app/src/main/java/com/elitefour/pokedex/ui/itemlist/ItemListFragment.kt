package com.elitefour.pokedex.ui.itemlist

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.elitefour.pokedex.PokedexApp

import com.elitefour.pokedex.R
import com.elitefour.pokedex.adapter.ItemListAdapter
import com.elitefour.pokedex.interfaces.OnClickListenerExtension
import com.elitefour.pokedex.managers.ItemListManager
import com.elitefour.pokedex.model.Item
import kotlinx.android.synthetic.main.fragment_item_list.*


class ItemListFragment : Fragment() {
    private val itemVM: ItemViewModel by viewModels()
    private lateinit var itemListManager: ItemListManager
    private lateinit var itemListAdapter: ItemListAdapter
    private lateinit var mainActivityListener: OnClickListenerExtension

    private lateinit var inputMethodManager: InputMethodManager

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnClickListenerExtension) {
            mainActivityListener = context
        }
        itemListManager = (context.applicationContext as PokedexApp).itemListManager
        itemListManager.initializeItemList()
        itemVM.init(itemListManager)
        inputMethodManager = getSystemService(context, InputMethodManager::class.java)!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvItemList.setOnTouchListener { v, event ->
            inputMethodManager.hideSoftInputFromWindow(item_search_view.windowToken, 0)
        }
        item_search_view.setOnClickListener {
            item_search_view.onActionViewExpanded()
        }
        item_search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                item_search_view.clearFocus()
                item_search_view.setQuery("", false)
                if (query != null) {
                    updateAdapter(itemVM.getQueriedItemList(query))
                }
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    updateAdapter(itemVM.getQueriedItemList(newText))
                }
                return true
            }
        })
        itemVM.itemListReady.observe(viewLifecycleOwner,  Observer { success ->
            if (success) {
                initAdapter()
                setItemClickListener()
            }
        })
    }

    private fun initAdapter() {
        itemLoading.visibility = View.GONE
        rvItemList.visibility = View.VISIBLE
        itemListAdapter = ItemListAdapter(itemListManager.getItemList())
        rvItemList.adapter = itemListAdapter
    }

    private fun updateAdapter(itemList: ArrayList<Item>) {
        itemListAdapter.change(itemList)
    }


    private fun setItemClickListener() {
        itemListAdapter.onItemClickListener = {item ->
            item_search_view.clearFocus()
            mainActivityListener.onItemClicked(item)
        }
    }
}
