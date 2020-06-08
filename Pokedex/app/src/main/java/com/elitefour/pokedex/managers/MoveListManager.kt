package com.elitefour.pokedex.managers

import android.content.Context
import android.util.Log
import com.elitefour.pokedex.model.Move
import com.elitefour.pokedex.model.MoveFullInfo
import com.google.gson.Gson
import java.io.IOException

class MoveListManager(context: Context)  {
    var moveListFullInfoReady: Boolean = false
    private var moveList: ArrayList<Move> = ArrayList()
    private var moveFullInfoList: ArrayList<MoveFullInfo> = ArrayList()

    companion object {
        const val NUM_OF_MOVES = 728 // All moves
        const val moveListDir = "movelist/move"
    }

    init {
        for (n in 1..NUM_OF_MOVES) {
            val fileName = "$moveListDir$n.json"
            val moveFullInfo = getGsonFromAsset(context, fileName)
            if (moveFullInfo != null) {
                moveFullInfoList.add(moveFullInfo)
            } else {
                Log.i("Manager", "Error adding move $n")
            }
        }
        Log.i("Manager", moveFullInfoList.size.toString())
    }

    private fun getGsonFromAsset(context: Context, fileName: String): MoveFullInfo? {
        var jsonString: String? = null
        val gson = Gson()
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
        }
        return gson.fromJson(jsonString, MoveFullInfo::class.java)
    }


}