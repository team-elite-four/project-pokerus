package com.elitefour.pokedex.model

data class Pokemon (
    val name: String,
    val url: String,
    val imageURL: String?,
    val types: List<TypeSlot>?
)

data class PokemonFullInfo (
    val abilities: List<AbilitySlot>,
    val base_experience: Int,
    val forms: List<Form>,
    val game_indices: List<GameIndex>,
    val height: Int,
    val held_items: List<ItemSlot>,
    val id: Int,
    val is_default: Boolean,
    val location_area_encounters: String,
    val moves: List<MoveInfo>,
    val name: String,
    val order: Int,
    val species: Species,
    val sprites: Sprites,
    val stats: List<StatBaseInfo>,
    val types: List<TypeSlot>,
    val weight: Int
)

data class AbilitySlot (
    val ability: Ability,
    val is_hidden: Boolean,
    val slot: Int
)

data class Form (
    val name: String,
    val url: String
)

data class GameIndex (
    val game_index: Int,
    val version: Version
)

data class Version (
    val name: String,
    val url: String
)

data class MoveInfo (
    val move: Move,
    val version_group_detail: VersionGroupDetail
)

data class VersionGroupDetail (
    val level_learn_at: Int,
    val move_learn_method: MoveLearnMethod,
    val version_group: VersionGroup
)

data class MoveLearnMethod (
    val name: String,
    val url: String
)

data class VersionGroup (
    val name: String,
    val url: String
)

data class Sprites (
    val back_default: String?,
    val back_female: String?,
    val back_shiny: String?,
    val back_shiny_female: String?,
    val front_default: String?,
    val front_female: String?,
    val front_shiny: String?,
    val front_shiny_female: String?
)

// Stat data class describes the value of a certain stat and the effort points
// It includes the type of the stat, base stat and effort
data class StatBaseInfo (
    val base_stat: Int,
    val effort: Int,
    val stat: Stat
)

data class TypeSlot (
    val slot: Int,
    val type: Type
)

data class PokemonSlot (
    val pokemon: Pokemon,
    val slot: Int
)
