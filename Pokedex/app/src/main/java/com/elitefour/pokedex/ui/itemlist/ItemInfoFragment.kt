package com.elitefour.pokedex.ui.itemlist

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.elitefour.pokedex.PokedexApp

import com.elitefour.pokedex.R
import com.elitefour.pokedex.managers.ItemListManager
import com.elitefour.pokedex.model.ItemInfo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_item_info.*

/**
 * A simple [Fragment] subclass.
 * Use the [ItemInfoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ItemInfoFragment : Fragment() {
    private val itemVM: ItemViewModel by viewModels()
    private var itemUrl: String = ""
    private lateinit var itemListManager: ItemListManager

    companion object {
        const val TAG = "ITEM_TAG"
        const val ITEM_INFO_URL = "ITEM_INFO_URL"

        fun getInstance(itemUrl: String): ItemInfoFragment {
            return ItemInfoFragment().apply {
                arguments = Bundle().apply {
                    putString(ITEM_INFO_URL, itemUrl)
                }
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        itemListManager = (context.applicationContext as PokedexApp).itemListManager
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        arguments?.let {args ->
            with (args) {
                getString(ITEM_INFO_URL)?.let { url ->
                    itemUrl = url
                }
            }
        }
        itemListManager.initializeItemInfo(itemUrl)
        itemVM.init(itemListManager)
        return inflater.inflate(R.layout.fragment_item_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemVM.itemInfoReady.observe(viewLifecycleOwner,  Observer { success ->
            if (success) {
                showInfo()
            }
        })
    }

    private fun showInfo() {
        itemDetailLoading.visibility = View.GONE
        clItemInfo.visibility = View.VISIBLE
        val itemInfo = itemListManager.getItemInfo()
        tvItemName.text = "${itemInfo.name}"
        tvItemFlavorText.text = "\"${itemInfo.flavor_text_entries[0].text}\""
        tvEffectText.text = "${itemInfo.effect_entries[0].effect}"
        tvCostText.text = itemInfo.cost.toString()
        Picasso.get().load(itemInfo.sprites.default).into(ivItemImage)
    }
}
