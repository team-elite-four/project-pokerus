package com.elitefour.pokedex.ui.pokedex.pokemoninfopager

import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.Dimension
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.elitefour.pokedex.R
import com.elitefour.pokedex.model.PokemonFullInfo
import com.elitefour.pokedex.ui.pokedex.PokedexViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_pokemon_info.*
import okhttp3.internal.Util


/**
 * A simple [Fragment] subclass.
 * Use the [PokemonInfoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PokemonInfoFragment : Fragment() {

    private val pokedexVM: PokedexViewModel by activityViewModels()
    private lateinit var pokemonFullInfo: PokemonFullInfo

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pokemon_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val url = pokedexVM.getCurrentPokemon().url
        pokedexVM.pokedexFullInfoSuccess.observe(viewLifecycleOwner,  Observer { success ->
            if (success) {
                if (pokedexVM.getPokemonFullInfo(url) != null) {
                    pokemonFullInfo = pokedexVM.getPokemonFullInfo(url)!!
                    updateUI(view)
                }
            }
        })
        pokedexVM.initializePokemonFullInfo(url)
    }

    // We need to decide on what information we include
    private fun updateUI(view: View) {
        Picasso.get().load(pokemonFullInfo.imageURL).into(ivItemImage)
        tvItemName.text = pokemonFullInfo.name.capitalize()
        pokeID.text = "# ${pokemonFullInfo.id}"
        pokeBaseExp.text = "${pokemonFullInfo.base_experience} EXP"
        pokemonFullInfo.types.let {types ->
            // Display second type
            if (types.size == 2 ) {
                val type = types[1].type.name
                pokeType2.text = type
                pokeType2.background.setTint(view.context.getColor(getColorResource(type)))
                pokeType2.visibility = View.VISIBLE
            } else {
                pokeType2.visibility = View.GONE
            }
            // Display first type
            if (types.isNotEmpty()) {
                val type = types[0].type.name
                pokeType1.text = type
                pokeType1.background.setTint(view.context.getColor(getColorResource(type)))
                // overall_container.setBackgroundColor(view.context.getColor(getColorResource(type)))
            }
        }
        displayBaseStat()
    }

    private fun displayBaseStat() {
        //val target = 50
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 65f, resources.displayMetrics)
        pokeHpStat.text = pokemonFullInfo.stats[0].base_stat.toString()
        pokeHpStatBar.width = dpToPx(pokemonFullInfo.stats[0].base_stat * 2)
        pokeAttStat.text = pokemonFullInfo.stats[1].base_stat.toString()
        pokeAttStatBar.width = dpToPx(pokemonFullInfo.stats[1].base_stat * 2)
        pokeDefStat.text = pokemonFullInfo.stats[2].base_stat.toString()
        pokeDefStatBar.width = dpToPx(pokemonFullInfo.stats[2].base_stat * 2)
        pokeSpAttStat.text = pokemonFullInfo.stats[3].base_stat.toString()
        pokeSpAttStatBar.width = dpToPx(pokemonFullInfo.stats[3].base_stat * 2)
        pokeSpDefStat.text = pokemonFullInfo.stats[4].base_stat.toString()
        pokeSpDefStatBar.width = dpToPx(pokemonFullInfo.stats[4].base_stat * 2)
        pokeSpdStat.text = pokemonFullInfo.stats[5].base_stat.toString()
        pokeSpdStatBar.width = dpToPx(pokemonFullInfo.stats[5].base_stat * 2)
    }

    private fun dpToPx(dp: Int): Int {
        return (dp * resources.displayMetrics.density).toInt()
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

    companion object {
        val TAG: String = PokemonInfoFragment::class.java.simpleName
        fun getInstance(): PokemonInfoFragment {
            return PokemonInfoFragment()
        }
    }
}
