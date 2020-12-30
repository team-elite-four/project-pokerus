package com.elitefour.pokedex.model

data class Type (
    val name: String,
    val url: String
)

data class TypeInfo (
    val damage_relations: DamageRelations,
    val game_indices: List<TypeGameIndex>,
    val generation: Generation,
    val id: Int,
    val move_damage_class: MoveDamageClass,
    val moves: List<Move>,
    val name: String,
    val names: List<Language>,
    val pokemon: List<PokemonSlot>
)

data class DamageRelations (
    val double_damage_from: List<Type>,
    val double_damage_to: List<Type>,
    val half_damage_from: List<Type>,
    val half_damage_to: List<Type>,
    val no_damage_from: List<Type>,
    val no_damage_to: List<Type>
)

data class Generation (
    val name: String,
    val url: String
)

data class Language(
    val name: String,
    val url: String
)

data class TypeGameIndex (
    val game_index: Int,
    val generation: Generation
)

data class PokemonSlot (
    val pokemon: Pokemon,
    val slot: Int
)


