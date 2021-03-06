package com.elitefour.pokedex.ui.itemlist

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elitefour.pokedex.interfaces.OnItemReadyListener
import com.elitefour.pokedex.managers.ItemListManager
import com.elitefour.pokedex.model.Item

class ItemViewModel: ViewModel(), OnItemReadyListener {
    private lateinit var itemListManager: ItemListManager
    var itemListReady = MutableLiveData<Boolean>()
    var itemInfoReady = MutableLiveData<Boolean>()

    fun init(manager: ItemListManager) {
        itemListManager = manager
        itemListReady.value = false
        itemInfoReady.value = false
        itemListManager.onItemReadyListener = this
    }

    /**
     * @return returns the queried result of the item list
     */
    fun getQueriedItemList(query: String): ArrayList<Item> {
        return itemListManager.getQueriedItemList(query)
    }

    override fun itemListReady() {
        itemListReady.value = true
    }

    override fun itemInfoReady() {
        itemInfoReady.value = true
    }
}