package com.elitefour.pokedex.interfaces

import com.elitefour.pokedex.model.Pokemon

interface OnClickListenerExtension {
    fun onPokemonClicked(pokemon: Pokemon)
}