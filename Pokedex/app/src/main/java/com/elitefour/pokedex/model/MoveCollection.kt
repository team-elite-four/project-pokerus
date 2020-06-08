package com.elitefour.pokedex.model

data class MoveCollection (
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Move>
)