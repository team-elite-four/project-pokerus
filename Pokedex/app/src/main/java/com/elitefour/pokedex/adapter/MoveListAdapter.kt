package com.elitefour.pokedex.adapter

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

class MoveListAdapter(moveListInitial: List<MoveFullInfo>): RecyclerView.Adapter<MoveListAdapter.MoveListViewHolder>() {

    private var moveList: List<MoveFullInfo> = moveListInitial
    var onMoveClickListener: ((moveFullInfo: MoveFullInfo) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoveListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_move, parent, false)
        return MoveListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return moveList.size
    }

    override fun onBindViewHolder(holder: MoveListViewHolder, position: Int) {
        val move = moveList[position]
        holder.bind(move, position)
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

        fun bind(moveFullInfo: MoveFullInfo, position: Int) {
            moveName.text = moveFullInfo.name.capitalize()
            moveDmg.text = moveFullInfo.power?.toString()
            moveFullInfo.type?.let { type ->
                moveType.text = type.name
                moveType.background.setTint(itemView.context.getColor(getTypeColorResource(type.name)))
            }
            moveFullInfo.damage_class?.let { damage_class ->
                moveClass.text = damage_class.name
                moveClass.background.setTint(itemView.context.getColor(getClassColorResource(damage_class.name)))
            }

            itemView.setOnClickListener {
                onMoveClickListener?.invoke(moveFullInfo)
            }

        }
    }
}