package com.elitefour.pokedex.model

data class Item (
    val name: String,
    val url: String
)

data class ItemInfo (
    val attributes: String,
    val babay_trigger_for: String?
)

// ArmMask Added this for Pokemon.kt

data class ItemSlot (
    val item: Item,
    val version_details: List<Version>
)

// TODO