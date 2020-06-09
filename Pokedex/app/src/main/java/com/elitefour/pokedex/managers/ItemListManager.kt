package com.elitefour.pokedex.managers

import android.content.Context
import android.util.Log
import com.elitefour.pokedex.PokedexApp
import com.elitefour.pokedex.interfaces.OnItemReadyListener
import com.elitefour.pokedex.model.Item
import com.elitefour.pokedex.model.ItemInfo

class ItemListManager(context: Context) {
    private var itemList: ArrayList<Item> = ArrayList()
    private lateinit var itemInfo: ItemInfo
    private val apiManager = (context.applicationContext as PokedexApp).apiManager
    var onItemReadyListener: OnItemReadyListener? = null


    fun initializeItemList() {
        apiManager.fetchItemList({list ->
            itemList = list as ArrayList<Item>
            itemList.forEachIndexed { index: Int, item: Item ->
                if (item.name.startsWith("tm")) {
                    itemList[index] = item.copy(imgUrl = APIManager.TM_ITEM_IMAGE_URL)
                } else {
                    itemList[index] = item.copy(imgUrl = apiManager.fetchItemImageUrl(item.name))
                }
            }
            onItemReadyListener?.itemListReady()
        }, {
            Log.i("Manager", "Fail to fetch item list")
        })
    }

    fun initializeItemInfo(itemUrl: String) {
        apiManager.fetchItemInfo(itemUrl, {info ->
            itemInfo = info
            onItemReadyListener?.itemInfoReady()
        }, {
            Log.i("Manager", "Fail to fetch item info from $itemUrl")
        })
    }

    fun getItemList(): ArrayList<Item> {
        return itemList
    }

    fun getItemInfo(): ItemInfo {
        return itemInfo
    }
}