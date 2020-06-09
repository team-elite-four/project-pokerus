package com.elitefour.pokedex.ui.pokedex

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
    var pokedexFullInfoSuccess = MutableLiveData<Boolean>()

    fun init(pokedexManager: PokedexManager) {

        pokedexNameImageSuccess.value = false
        pokedexTypeSuccess.value = false
        pokedexFullInfoSuccess.value = false

        // If we leave app this VM is destroyed. So the pokedexTypesuccess will be set to false
        // even tho it is true since we ONLY left the app
        if (pokedexManager.pokedexNameImageReady && pokedexManager.pokedexTypeReady) {
            pokedexFullInfoSuccess.value = true
            pokedexTypeSuccess.value = true
        }

        this.pokedexManager = pokedexManager
        if (!pokedexManager.pokedexNameImageReady or !pokedexManager.pokedexTypeReady) {
            pokedexManager.onPokedexReadyListener = this
        }
    }

    /**
     * @return the current pokemon list from the PokedexManager
     */
    fun getPokemonList(): List<Pokemon> {
        return pokedexManager.getPokemonList()
    }

    /**
     * @return the current pokemon info from the PokedexManager, or null otherwise,
    * with a promise of calling a mutable live data observer
    */
    fun getPokemonFullInfo(url: String): PokemonFullInfo? {
        val id = pokedexManager.getIDFromPokemonURL(url)
        return pokedexManager.getPokemonFullInfo(id)
    }

    /**
     *
     * with a promise of calling a mutable live data observer
     */
    fun initializePokemonFullInfo(url: String) {
        pokedexManager.initializePokemonFullInfo(url)
    }


    /**
     * This method is called from the pokedex manager and will
     * notify any observers for [pokedexNameImageSuccess]
     */
    override fun pokedexNameImageReady() {
        pokedexNameImageSuccess.value = true
    }

    /**
     * This method is called from the pokedex manager and will
     * notify any observers for [pokedexTypeSuccess]
     */
    override fun pokedexTypeReady() {
        pokedexTypeSuccess.value = true
    }

    /**
     * This method is called from the pokedex manager and will
     * notify any observers for [pokedexFullInfoSuccess]
     */
    override fun pokedexFullInfoReady() {
        pokedexFullInfoSuccess.value = true
    }
}