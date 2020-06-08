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

class MoveListAdapter(moveListInitial: List<Move>): RecyclerView.Adapter<MoveListAdapter.MoveListViewHolder>() {

    private var moveList: List<Move> = moveListInitial
    var onMoveClickListener: ((move: Move) -> Unit)? = null

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

    private fun getColorResource(type: String): Int {
        return when(type) {
            "physical" -> R.color.classPhysical
            "special" -> R.color.classSpecial
            "status" -> R.color.classStatus
            else -> R.color.classUnknown
        }
    }

    inner class MoveListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val moveName = itemView.findViewById<TextView>(R.id.moveName)
        private val moveDmg = itemView.findViewById<TextView>(R.id.moveDmg)
        private val moveType = itemView.findViewById<Button>(R.id.moveType)
        private val moveClass = itemView.findViewById<Button>(R.id.moveClass)

        fun bind(move: Move, position: Int) {
            moveName.text = move.name
            moveDmg.text = move.power?.toString()
            move.type?.let { type ->
                moveType.text = type
            }
            move.damage_class?.let { damage_class ->
                moveClass.text = damage_class
            }

            itemView.setOnClickListener {
                onMoveClickListener?.invoke(move)
            }

        }
    }
}