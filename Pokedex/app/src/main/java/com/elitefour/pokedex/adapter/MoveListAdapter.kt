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
import com.elitefour.pokedex.model.Pokemon
import com.squareup.picasso.Picasso

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
        private val pokeNumber = itemView.findViewById<TextView>(R.id.moveName)
        private val pokeName = itemView.findViewById<TextView>(R.id.moveDmg)
        private val pokeImage = itemView.findViewById<ImageView>(R.id.moveType)
        private val pokeType1 = itemView.findViewById<Button>(R.id.moveClass)
        //private val pokeType2 = itemView.findViewById<Button>(R.id.movePP)

        fun bind(move: Move, position: Int) {


            itemView.setOnClickListener {
                onMoveClickListener?.invoke(move)
            }

        }
    }
}