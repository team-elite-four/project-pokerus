package com.elitefour.pokedex.ui.movelist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elitefour.pokedex.managers.APIManager
import com.elitefour.pokedex.managers.MoveListManager

import com.elitefour.pokedex.model.MoveFullInfo
import com.elitefour.pokedex.model.MoveInfo

class MoveListViewModel: ViewModel() {

    private lateinit var moveListManager: MoveListManager

    //    var moveListSuccess = MutableLiveData<Boolean>(false)
    var moveListFiltered = MutableLiveData<ArrayList<MoveFullInfo>>()

    fun init(moveListManager: MoveListManager) {
        this.moveListManager = moveListManager
        this.moveListFiltered.value = moveListManager.getMoveFullInfoList() as ArrayList<MoveFullInfo>
    }

    fun filterMoveList(moveList: ArrayList<MoveInfo>) {
        var filteredList: ArrayList<MoveFullInfo> = ArrayList()
        moveListFiltered.value?.let { fullMoveList->
            moveList.forEach {moveInfo ->
                val index = getIndexFromPokemonURL(moveInfo.move.url)
                if (index != -1) {
                    filteredList.add(fullMoveList[index])
                }
            }
            moveListFiltered.value = filteredList
        }
    }


    // Assumes the manager always will be able to get full info at init
    fun getFilteredMoveList(): ArrayList<MoveFullInfo> {
        return moveListFiltered.value!!
    }

    // Assumes the manager always will be able to get full info at init
    fun getFullMoveList(): ArrayList<MoveFullInfo> {
        return moveListManager.getMoveFullInfoList() as ArrayList<MoveFullInfo>
    }

    fun getIndexFromPokemonURL(url: String): Int {
        val arr = url.split("/")
        val limit = MoveListManager.NUM_OF_MOVES
        val id = arr[arr.lastIndex-1].toInt()
        return if (id > limit) -1 else (id - 1)
    }
}