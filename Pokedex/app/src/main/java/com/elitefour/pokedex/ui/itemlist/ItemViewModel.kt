package com.elitefour.pokedex.ui.itemlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elitefour.pokedex.interfaces.OnItemListReadyListener
import com.elitefour.pokedex.managers.ItemListManager
import com.elitefour.pokedex.model.Item

class ItemViewModel: ViewModel(), OnItemListReadyListener {
    private lateinit var itemListManager: ItemListManager
    var itemListReady = MutableLiveData<Boolean>()

    fun init(manager: ItemListManager) {
        itemListManager = manager
        itemListReady.value = false
    }

    fun getItemList(): ArrayList<Item> {
        return itemListManager.getItemList()
    }

    override fun itemListReady() {
        itemListReady.value = true
    }
}