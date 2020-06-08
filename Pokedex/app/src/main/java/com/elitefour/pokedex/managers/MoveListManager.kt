package com.elitefour.pokedex.managers

import android.content.Context
import com.elitefour.pokedex.model.Move
import com.elitefour.pokedex.model.MoveFullInfo

class MoveListManager(context: Context)  {
    var moveListFullInfoReady: Boolean = false
    private var moveList: ArrayList<Move> = ArrayList()
    private var moveFullInfoList: ArrayList<MoveFullInfo> = ArrayList()

    init {

    }
}