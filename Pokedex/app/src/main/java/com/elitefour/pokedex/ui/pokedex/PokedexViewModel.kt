package com.elitefour.pokedex.ui.pokedex

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elitefour.pokedex.interfaces.OnPokedexReadyListener
import com.elitefour.pokedex.managers.PokedexManager
import com.elitefour.pokedex.model.Pokemon


class PokedexViewModel : ViewModel(), OnPokedexReadyListener{

    private lateinit var pokedexManager: PokedexManager

    var pokedexNameImageSuccess = MutableLiveData<Boolean>()
    var pokedexTypeSuccess = MutableLiveData<Boolean>()

    fun init(pokedexManager: PokedexManager) {

        pokedexNameImageSuccess.value = false
        pokedexTypeSuccess.value = false

        this.pokedexManager = pokedexManager
        if (!pokedexManager.pokedexNameImageReady or !pokedexManager.pokedexTypeReady) {
            pokedexManager.onPokedexReadyListener = this
        }
    }

    /**
     * Update the current list of pokemon
     */
    fun getPokemonList(): List<Pokemon> {
        return pokedexManager.getPokemonList()
    }

    override fun pokedexNameImageReady() {
        pokedexNameImageSuccess.value = true
    }

    override fun pokedexTypeReady() {
        pokedexTypeSuccess.value = true
    }
}