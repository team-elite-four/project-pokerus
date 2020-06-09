package com.elitefour.pokedex.interfaces

import com.elitefour.pokedex.model.*

interface OnClickListenerExtension {
    fun onPokemonClicked(pokemon: Pokemon)
    fun onItemClicked(item: Item)
    fun onMoveClicked(moveFullInfo: MoveFullInfo)
    //fun onAbilityClicked(ability: Ability)
}