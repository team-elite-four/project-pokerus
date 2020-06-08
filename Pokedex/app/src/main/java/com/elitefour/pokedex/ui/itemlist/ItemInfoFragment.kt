package com.elitefour.pokedex.ui.itemlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.elitefour.pokedex.R

/**
 * A simple [Fragment] subclass.
 * Use the [ItemInfoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ItemInfoFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item_info, container, false)
    }

    companion object {

    }
}
