package com.elitefour.pokedex.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.elitefour.pokedex.R
import com.elitefour.pokedex.model.Pokemon
import com.squareup.picasso.Picasso

class FavoritesAdapter(initList: ArrayList<Pokemon>): RecyclerView.Adapter<FavoritesAdapter.PokemonListViewHolder>() {
    private var pokemonList: List<Pokemon> = initList
    var onPokemonClickListener: ((pokemon: Pokemon) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false)
        return PokemonListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    override fun onBindViewHolder(holder: PokemonListViewHolder, position: Int) {
        holder.bind(pokemonList[position], position)
    }

    private fun getColorResource(type: String): Int {
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

    inner class PokemonListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val pokeNumber = itemView.findViewById<TextView>(R.id.pokeID)
        private val pokeName = itemView.findViewById<TextView>(R.id.tvPokemonName)
        private val pokeImage = itemView.findViewById<ImageView>(R.id.ivItemImage)
        private val pokeType1 = itemView.findViewById<Button>(R.id.pokeType1)
        private val pokeType2 = itemView.findViewById<Button>(R.id.pokeType2)

        fun bind(pokemon: Pokemon, position: Int) {
            pokeNumber.text = "# ${getIDFromPokemonURL(pokemon.url)}"
            pokeName.text = pokemon.name.capitalize()
            pokemon.types?.let {types ->
                // Display second type
                if (types.size == 2 ) {
                    val type = types[1].type.name
                    pokeType2.text = type
                    pokeType2.background.setTint(itemView.context.getColor(getColorResource(type)))
                    pokeType2.visibility = View.VISIBLE
                } else {
                    pokeType2.visibility = View.GONE
                }
                // Display first type
                if (types.isNotEmpty()) {
                    val type = types[0].type.name
                    pokeType1.text = type
                    pokeType1.background.setTint(itemView.context.getColor(getColorResource(type)))
                }
            }
            Picasso.get().load(pokemon.imageURL).into(pokeImage)

            itemView.setOnClickListener {
                onPokemonClickListener?.invoke(pokemon)
            }
        }

        private fun getIDFromPokemonURL(url: String): Int {
            val arr = url.split("/")
            return arr[arr.lastIndex-1].toInt()
        }
    }
}