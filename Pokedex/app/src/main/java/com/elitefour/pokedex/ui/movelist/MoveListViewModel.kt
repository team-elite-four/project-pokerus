package com.elitefour.pokedex.ui.movelist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elitefour.pokedex.managers.APIManager
import com.elitefour.pokedex.model.Move

class MoveListViewModel: ViewModel() {

    private lateinit var apiManager: APIManager

    var moveListFiltered = MutableLiveData<ArrayList<Move>>()

}