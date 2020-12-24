package com.elitefour.pokedex.model

data class TypeCollection(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Type>
)