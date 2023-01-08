package com.garciafrancisco.pokedex.ui.pokemonlist

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.garciafrancisco.pokedex.R
import com.garciafrancisco.pokedex.data.models.PokedexListEntry

private const val TAG: String = "PokemonListAdapter"

class PokemonListAdapter : RecyclerView.Adapter<PokemonViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<PokedexListEntry>() {

        override fun areItemsTheSame(
            oldItem: PokedexListEntry,
            newItem: PokedexListEntry
        ): Boolean {
            return oldItem.pokemonName == newItem.pokemonName
        }

        override fun areContentsTheSame(
            oldItem: PokedexListEntry,
            newItem: PokedexListEntry
        ): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        Log.d(TAG, "onCreateViewHolder()")

        return PokemonViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.pokemon_list_entry,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder()")

        val pokedexEntry = differ.currentList[position]

        holder.apply {
            Glide.with(this.itemView.context).load(pokedexEntry.imageUrl).into(pokemonSprite)
            pokemonName.text = pokedexEntry.pokemonName
            container.setOnClickListener {
                onItemClickListener?.let { listener -> listener(pokedexEntry.pokeApiId) }
            }
        }
    }

    private var onItemClickListener: ((Int) -> Unit)? = null

    fun setOnItemClickListener(listener: (Int) -> Unit) {
        onItemClickListener = listener
    }

    override fun getItemCount(): Int {
        Log.d(TAG, "getItemCount(): ${differ.currentList.size}")
        return differ.currentList.size
    }
}