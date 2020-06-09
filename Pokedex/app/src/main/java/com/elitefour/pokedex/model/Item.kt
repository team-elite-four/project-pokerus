package com.elitefour.pokedex.model

data class Item (
    val name: String,
    val url: String,
    val imgUrl: String
)

data class ItemInfo (
   /* val attributes: String,
    val baby_trigger_for: String?,*/
    val category: Category,
    val cost: Int,
    val effect_entries: List<EffectEntry>,
    val flavor_text_entries: List<ItemFlavorTextEntry>,
    val game_indices: List<GameIndex>,
    val held_by_pokemon: List<HeldPokemon>,
    val id: Int,
    val name: String,
    val names: List<ItemNameLang>,
    val sprites: ItemSprite
)

data class ItemSprite (
    val default: String
)

data class ItemFlavorTextEntry (
    val language: Language,
    val text: String,
    val version_group: VersionGroup
)

data class HeldPokemon (
    val pokemon: Pokemon,
    val version_details: List<VersionGroupRarity>
)

data class ItemNameLang(
    val language: Language,
    val name: String
)

data class VersionGroupRarity (
    val rarity: Int,
    val version: VersionGroup
)

// ArmMask Added this for Pokemon.kt

data class ItemSlot (
    val item: Item,
    val version_details: List<Version>
)

// TODO