package com.elitefour.pokedex.interfaces

import com.elitefour.pokedex.model.Ability
import com.elitefour.pokedex.model.Item
import com.elitefour.pokedex.model.Move
import com.elitefour.pokedex.model.Pokemon

interface OnClickListenerExtension {
    fun onPokemonClicked(pokemon: Pokemon)
    fun onItemClicked(item: Item)
    fun onMoveClicked(move: Move)
    //fun onAbilityClicked(ability: Ability)
}