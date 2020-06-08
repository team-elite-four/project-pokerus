package com.elitefour.pokedex.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Move(
    val name: String,
    val url: String,
    var damage_class: String?,
    var type: String?,
    var power: Int?
): Parcelable

data class MoveFullInfo(
    val accuracy: Int,
    //val contest_effect: ContestEffect,
    //val contest_type: ContestType,
    val damage_class: MoveDamageClass,
    val effect_chance: Int,
    //val effect_changes:  List<EffectChange>,
    val effect_entries: List<EffectEntry>,
    val flavor_text_entries: List<FlavorTextEntry>,
    val flavor_text_description: String,
    val generation: Generation,
    val id: Int,
    //val machines: List<MachineEntry>,
    val meta: Meta,
    val name: String,
    //val names: List<NameEntry>
    //val past_values: List<MoveValueEntry>
    val power: Int,
    val pp: Int,
    val priority: Int,
    val stat_changes: List<StatEntry>,
    //val super_contest_effect: SuperContestEffect,
    val target: MoveTarget,
    val type: Type
)

data class EffectEntry(
    val effect: String,
    val language: Language,
    val short_effect: String
)

data class FlavorTextEntry(
    val flavor_text: String,
    val language: Language,
    val version_group: VersionGroup
)

data class Meta(
    val ailment: Ailment,
    val ailment_chance: Int,
    val category: Category,
    val crit_rate: Int,
    val drain: Int,
    val flinch_chance: Int,
    val healing: Int,
    val max_hits: Int,
    val max_turns: Int,
    val min_hits: Int,
    val min_turns: Int,
    val stat_chance: Int
)

data class Category(
    val name: String,
    val url: String
)

data class Ailment(
    val name: String,
    val url: String
)

data class MoveTarget(
    val name: String,
    val url: String
)

data class MoveDamageClass(
    val name: String,
    val url: String
)
