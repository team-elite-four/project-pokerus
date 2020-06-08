package com.elitefour.pokedex.managers

import android.content.Context
import android.util.Log
import com.elitefour.pokedex.PokedexApp
import com.elitefour.pokedex.interfaces.OnItemListReadyListener
import com.elitefour.pokedex.model.Item

class ItemListManager(context: Context) {
    private var itemList: ArrayList<Item> = ArrayList()
    private val apiManager = (context.applicationContext as PokedexApp).apiManager
    var onItemListReadyListener: OnItemListReadyListener? = null


    fun initializeItemList() {
        apiManager.fetchItemList({list ->
            Log.i("Manager", "item list fetch success")
            itemList = list as ArrayList<Item>
            itemList.forEachIndexed {index: Int, item: Item ->
                itemList[index] = item.copy(imgUrl = apiManager.fetchItemImageUrl(item.name))
            }
            onItemListReadyListener?.itemListReady()
        }, {
            Log.i("Manager", "Fail to fetch item list")
        })
    }

    fun getItemList(): ArrayList<Item> {
        return itemList
    }
}