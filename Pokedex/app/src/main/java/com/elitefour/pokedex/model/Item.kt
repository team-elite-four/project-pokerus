package com.elitefour.pokedex.model

data class Item (
    val name: String,
    val url: String,
    val imgUrl: String
)

data class ItemInfo (
    val attributes: String,
    val baby_trigger_for: String?,
    val category: Category,
    val effect_entries: List<EffectEntry>,
    val flavor_text_entries: List<FlavorTextEntry>,
    val game_indices: List<GameIndex>,
    val held_by_pokemon: List<Pokemon>,
    val id: Int,
    val name: String,
    val sprites: Sprites
)

// ArmMask Added this for Pokemon.kt

data class ItemSlot (
    val item: Item,
    val version_details: List<Version>
)

// TODO