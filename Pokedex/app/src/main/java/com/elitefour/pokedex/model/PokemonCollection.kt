package com.elitefour.pokedex.model

data class PokemonCollection (
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Pokemon>
)