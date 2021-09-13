package com.fabulouszanna.fabpokedex.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.fabulouszanna.fabpokedex.data.pokemon.SchematicPokemon
import com.fabulouszanna.fabpokedex.databinding.PokemonCardBinding
import com.fabulouszanna.fabpokedex.other.extractColorResourceFromType
import com.fabulouszanna.fabpokedex.other.retrievePokemonImage
import javax.inject.Inject

class PokemonListAdapter @Inject constructor(private val glide: RequestManager) :
    RecyclerView.Adapter<PokemonListAdapter.PokemonViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<SchematicPokemon>() {
        override fun areItemsTheSame(
            oldItem: SchematicPokemon,
            newItem: SchematicPokemon
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: SchematicPokemon,
            newItem: SchematicPokemon
        ): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var pokemonListItems: List<SchematicPokemon>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder =
        PokemonViewHolder(
            PokemonCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = pokemonListItems[position]
        holder.bind(pokemon)
    }

    override fun getItemCount(): Int = pokemonListItems.size

    inner class PokemonViewHolder(private val binding: PokemonCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val context = binding.root.context

        fun bind(pokemon: SchematicPokemon) {
            val typeColor = extractColorResourceFromType(context, pokemon.type)
            binding.apply {
                pokemonName.text = pokemon.name
                val pokemonImg = retrievePokemonImage(context, pokemon.imgUrl)
                glide.load(pokemonImg).into(pokemonPic)

                root.setCardBackgroundColor(typeColor)
            }
        }
    }
}