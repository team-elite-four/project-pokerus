package com.elitefour.pokedex.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.elitefour.pokedex.R
import com.elitefour.pokedex.model.Move
import com.elitefour.pokedex.model.MoveFullInfo
import com.elitefour.pokedex.model.MoveInfo
import org.w3c.dom.Text


class MoveListAdapter(moveListInitial: List<MoveFullInfo>, moveInfoList: List<MoveInfo>? = null): RecyclerView.Adapter<MoveListAdapter.MoveListViewHolder>() {

    private var moveList: List<MoveFullInfo> = moveListInitial
    private var moveInfoList: List<MoveInfo>? = moveInfoList
    var onMoveClickListener: ((moveFullInfo: MoveFullInfo) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoveListViewHolder {

        val view = if (moveInfoList == null) {
            LayoutInflater.from(parent.context).inflate(R.layout.item_move, parent, false)
        } else {
            LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon_move, parent, false)
        }
        return MoveListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return moveList.size
    }

    override fun onBindViewHolder(holder: MoveListViewHolder, position: Int) {
        holder.bind(moveList[position], moveInfoList?.get(position))
    }

    fun change(newMoveList: List<MoveFullInfo>) {
        moveList = newMoveList
        notifyDataSetChanged()
    }

    private fun getClassColorResource(type: String): Int {
        return when(type) {
            "physical" -> R.color.classPhysical
            "special" -> R.color.classSpecial
            "status" -> R.color.classStatus
            else -> R.color.classUnknown
        }
    }

    private fun getTypeColorResource(type: String): Int {
        return when(type) {
            "bug" -> R.color.typeBug
            "dragon" -> R.color.typeDragon
            "dark" -> R.color.typeDark
            "electric" -> R.color.typeElectric
            "fairy" -> R.color.typeFairy
            "fighting" -> R.color.typeFighting
            "fire" -> R.color.typeFire
            "flying" -> R.color.typeFlying
            "ghost" -> R.color.typeGhost
            "grass" -> R.color.typeGrass
            "ground" -> R.color.typeGround
            "ice" -> R.color.typeIce
            "normal" -> R.color.typeNormal
            "poison" -> R.color.typePoison
            "psychic" -> R.color.typePsychic
            "rock" -> R.color.typeRock
            "steel" -> R.color.typeSteel
            "water" -> R.color.typeWater
            else -> R.color.typeUnknown
        }
    }

    inner class MoveListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val moveName = itemView.findViewById<TextView>(R.id.moveName)
        private val moveDmg = itemView.findViewById<TextView>(R.id.moveDmg)
        private val moveType = itemView.findViewById<Button>(R.id.moveType)
        private val moveClass = itemView.findViewById<Button>(R.id.moveClass)
        private val moveLearnLevel = itemView.findViewById<TextView>(R.id.moveLearnLevel)

        fun bind(moveFullInfo: MoveFullInfo, moveInfo: MoveInfo?) {
            moveName.text = moveFullInfo.name.capitalize()
            moveDmg.text = moveFullInfo.power.toString()
            moveType.text = moveFullInfo.type.name
            moveType.background.setTint(itemView.context.getColor(getTypeColorResource(moveFullInfo.type.name)))
            moveClass.text = moveFullInfo.damage_class.name
            moveClass.background.setTint(itemView.context.getColor(getClassColorResource(moveFullInfo.damage_class.name)))
            if (moveInfo != null) {
                bindExtraMoveInfo(moveInfo, moveLearnLevel)
            }
            itemView.setOnClickListener {
                onMoveClickListener?.invoke(moveFullInfo)
            }
        }

        private fun bindExtraMoveInfo(moveInfo: MoveInfo, moveLearnLevel: TextView){
            val learningInfo = moveInfo.version_group_details.last()
            if (learningInfo.move_learn_method.name == "level-up") {
                moveLearnLevel.text = "Lv. ${learningInfo.level_learned_at}"
            } else if (learningInfo.move_learn_method.name == "machine") {
                moveLearnLevel.text = "TM/HM"
            } else if (learningInfo.move_learn_method.name == "tutor") {
                moveLearnLevel.text = "Tutor"
            } else {
                moveLearnLevel.text = "Other"
            }
        }
    }


}