package com.elitefour.pokedex.ui.setting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels

import com.elitefour.pokedex.R
import com.elitefour.pokedex.ui.favorites.FavoritesViewModel
import kotlinx.android.synthetic.main.fragment_setting.*


class SettingFragment : Fragment() {

    private val favoritesVM: FavoritesViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clearFavBtn.setOnClickListener {
            favoritesVM.clearFavorites()
        }
    }

    companion object {
    }
}
