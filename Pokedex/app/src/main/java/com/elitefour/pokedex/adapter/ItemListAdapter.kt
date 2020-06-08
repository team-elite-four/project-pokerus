package com.elitefour.pokedex.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.elitefour.pokedex.R
import com.elitefour.pokedex.model.Item
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_item.view.*

class ItemListAdapter(private var itemList: List<Item>): RecyclerView.Adapter<ItemListAdapter.ItemListViewHolder>() {
    inner class ItemListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val ivItemImage = itemView.findViewById<ImageView>(R.id.ivItemImage)
        val tvItemName = itemView.findViewById<TextView>(R.id.tvItemName)

        fun bind(item: Item) {
            Picasso.get().load(item.imgUrl).into(ivItemImage)
            tvItemName.text = item.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_item, parent, false)
        return ItemListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ItemListViewHolder, position: Int) {
        holder.bind(itemList[position])
    }
}