package com.elitefour.pokedex.model

data class ItemCollection (
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Item>
)