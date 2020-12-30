package com.elitefour.pokedex.ui.movelist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elitefour.pokedex.managers.MoveListManager

import com.elitefour.pokedex.model.MoveFullInfo
import com.elitefour.pokedex.model.MoveInfo

class MoveListViewModel: ViewModel() {

    private lateinit var moveListManager: MoveListManager

    private var moveListFiltered = MutableLiveData<ArrayList<MoveFullInfo>>()
    private lateinit var currentMove: MoveFullInfo

    fun init(moveListManager: MoveListManager) {
        this.moveListManager = moveListManager
        this.moveListFiltered.value = moveListManager.getMoveFullInfoList() as ArrayList<MoveFullInfo>
    }

    fun convertMoveList(moveList: ArrayList<MoveInfo>) {
        moveListFiltered.value = getFullMoveList()
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

    fun getQueriedMoveList(query: String): ArrayList<MoveFullInfo> {
        return moveListManager.getQueriedMoveList(query)
    }

    // Assumes the manager always will be able to get full info at init
    fun getConvertedMoveList(): ArrayList<MoveFullInfo> {
        return moveListFiltered.value!!
    }

    // Assumes the manager always will be able to get full info at init
    fun getFullMoveList(): ArrayList<MoveFullInfo> {
        return moveListManager.getMoveFullInfoList() as ArrayList<MoveFullInfo>
    }

    fun setCurrentMove(move: MoveFullInfo) {
        currentMove = move
    }

    fun getCurrentMove(): MoveFullInfo {
        return currentMove
    }

    private fun getIndexFromPokemonURL(url: String): Int {
        val arr = url.split("/")
        val limit = MoveListManager.NUM_OF_MOVES
        val id = arr[arr.lastIndex-1].toInt()
        return if (id > limit) -1 else (id - 1)
    }
}