package com.elitefour.pokedex.managers

import android.content.Context
import android.util.Log
import com.elitefour.pokedex.PokedexApp
import com.elitefour.pokedex.model.Item

class ItemListManager(context: Context) {
    private var itemList: ArrayList<Item> = ArrayList()
    private val apiManager = (context.applicationContext as PokedexApp).apiManager
    var itemListReady: Boolean = false

    fun initializeItemList() {
        apiManager.fetchItemList({list ->
            itemList = list as ArrayList<Item>
            itemList.forEach {item ->
                item.copy(imgUrl = apiManager.fetchItemImageUrl(item.name))
            }
            itemListReady = true
        }, {
            Log.i("Manager", "Fail to fetch item list")
        })
    }

    fun getItemList(): ArrayList<Item> {
        return itemList
    }
}