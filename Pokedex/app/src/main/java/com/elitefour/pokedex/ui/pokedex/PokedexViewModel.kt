package com.elitefour.pokedex.ui.pokedex

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elitefour.pokedex.interfaces.OnPokedexReadyListener
import com.elitefour.pokedex.managers.PokedexManager
import com.elitefour.pokedex.model.Pokemon
import com.elitefour.pokedex.model.PokemonFullInfo


class PokedexViewModel : ViewModel(), OnPokedexReadyListener{

    private lateinit var pokedexManager: PokedexManager

    var pokedexNameImageSuccess = MutableLiveData<Boolean>()
    var pokedexTypeSuccess = MutableLiveData<Boolean>()
    var pokemonInfoFetchSuccess = MutableLiveData<Boolean>()

    fun init(pokedexManager: PokedexManager) {

        pokedexNameImageSuccess.value = false
        pokedexTypeSuccess.value = false

        this.pokedexManager = pokedexManager
        if (!pokedexManager.pokedexNameImageReady or !pokedexManager.pokedexTypeReady) {
            pokedexManager.onPokedexReadyListener = this
        }
    }

//    /**
//     * @re
//     */
//    fun initList() {
//        if (!pokedexManager.dataReady) {
//            pokedexManager.onPokedexReadyListener = this
//        }
//    }

    /**
     * Update the current list of pokemon
     */
    fun getPokemonList(): List<Pokemon> {
        return pokedexManager.getPokemonList()
    }

    fun getPokemonInfo(url: String): PokemonFullInfo? {
        return pokedexManager.getPokemonInfo(url)
    }

    override fun pokedexNameImageReady() {
        pokedexNameImageSuccess.value = true
    }

    override fun pokedexTypeReady() {
        pokedexTypeSuccess.value = true
    }

    override fun readyInfo() {
        pokemonInfoFetchSuccess.value = true
    }
}