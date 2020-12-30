package com.elitefour.pokedex.model

// Stat data class describes the type of the stat
// It includes the name of the stat and its url for detailed information
// There should only be a total of 6 stat types
data class Stat (
    val name: String,
    val url: String
)

data class StatEntry(
    val change: Int,
    val stat: Stat
)