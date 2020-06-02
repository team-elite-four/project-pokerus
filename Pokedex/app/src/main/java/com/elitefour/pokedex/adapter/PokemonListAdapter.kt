package com.elitefour.pokedex.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.elitefour.pokedex.R
import com.elitefour.pokedex.model.Pokemon
import com.elitefour.pokedex.ui.pokedex.PokedexViewModel
import com.squareup.picasso.Picasso

class PokemonListAdapter(pokemonListInitial: ArrayList<Pokemon>, viewModelInitial: PokedexViewModel): RecyclerView.Adapter<PokemonListAdapter.PokemonListViewHolder>() {
    private var pokemonList: ArrayList<Pokemon> = pokemonListInitial
    private val viewModel: PokedexViewModel = viewModelInitial
    var onPokemonClickListener: ((pokemon: Pokemon) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false)
        return PokemonListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    override fun onBindViewHolder(holder: PokemonListViewHolder, position: Int) {
        val pokemon = pokemonList[position]
        holder.bind(pokemon, position)
    }

    inner class PokemonListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val pokeNumber = itemView.findViewById<TextView>(R.id.pokeNumber)
        private val pokeName = itemView.findViewById<TextView>(R.id.pokeName)
        private val pokeImage = itemView.findViewById<ImageView>(R.id.pokeImage)

        fun bind(pokemon: Pokemon, position: Int) {
            pokeNumber.text = (position + 1).toString()
            pokeName.text = pokemon.name
            Picasso.get().load(viewModel.getPokemonImageResource(position + 1)).into(pokeImage)

            itemView.setOnClickListener {
                onPokemonClickListener?.invoke(pokemon)
            }
        }
    }
}