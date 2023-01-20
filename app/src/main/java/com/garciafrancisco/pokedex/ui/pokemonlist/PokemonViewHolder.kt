package com.garciafrancisco.pokedex.ui.pokemonlist

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.garciafrancisco.pokedex.R

class PokemonViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val container: ConstraintLayout = itemView.findViewById(R.id.cl_pokemon_list_entry)
    val pokemonName: TextView = itemView.findViewById(R.id.tv_pokemon_name)
    val pokemonSprite: ImageView = itemView.findViewById(R.id.iv_pokemon_image)
}