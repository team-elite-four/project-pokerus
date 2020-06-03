package com.elitefour.pokedex.model

data class PokemonInfo (
    val name: String,
    val url: String,
    val imageURL: String,
    val types: List<TypeSlot>
)