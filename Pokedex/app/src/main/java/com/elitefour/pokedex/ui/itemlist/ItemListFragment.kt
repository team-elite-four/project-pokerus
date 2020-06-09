package com.elitefour.pokedex.ui.itemlist

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.elitefour.pokedex.PokedexApp

import com.elitefour.pokedex.R
import com.elitefour.pokedex.adapter.ItemListAdapter
import com.elitefour.pokedex.managers.ItemListManager
import kotlinx.android.synthetic.main.fragment_item_list.*


/**
 * A simple [Fragment] subclass.
 * Use the [ItemListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ItemListFragment : Fragment() {
    private val itemVM: ItemViewModel by viewModels()
    private lateinit var itemListManager: ItemListManager
    private lateinit var itemListAdapter: ItemListAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)

        itemListManager = (context.applicationContext as PokedexApp).itemListManager
        itemListManager.initializeItemList()
        itemVM.init(itemListManager)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

        itemVM.itemListReady.observe(viewLifecycleOwner,  Observer { success ->
            if (success) {
                initAdapter()
            }
        })
    }

    private fun initAdapter() {
        itemLoading.visibility = View.GONE
        rvItemList.visibility = View.VISIBLE
        itemListAdapter = ItemListAdapter(itemListManager.getItemList())
        rvItemList.adapter = itemListAdapter
    }
}
