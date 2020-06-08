package com.elitefour.pokedex.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Move(
    val name: String,
    val url: String
): Parcelable

//data class MoveFullInfo(
//    val accuracy: Int,
//    val contest_combos: ContestCombos,
//    val contest_effect: ContestEffect,
//    val contest_type: ContestType,
//    val damage_class: MoveDamageClass,
//    val effect_chance: Int,
//    val effect_changes:  List<EffectChange>,
//    val effect_entries: List<EffectEntry>,
//    val flavor_text_entries: List<FlavorTextEntry>,
//    val generation: Generation,
//    val id: Int,
//    val machines: List<MachineEntry>,
//    val meta: Meta,
//    val name: String,
//    val names: List<NameEntry>
//    val past_values: List<MoveValueEntry>
//    val power: Int,
//    val pp: Int,
//    val priority: Int,
//    val stat_changes: List<StatEntry>,
//    val super_contest_effect: SuperContestEffect,
//    val target: MoveTarget,
//    val type: Type
//)

data class MoveDamageClass(
    val name: String,
    val url: String
)