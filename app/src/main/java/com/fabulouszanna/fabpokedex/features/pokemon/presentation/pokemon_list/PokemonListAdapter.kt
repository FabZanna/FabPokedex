package com.fabulouszanna.fabpokedex.features.pokemon.presentation.pokemon_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.fabulouszanna.fabpokedex.core.util.retrieveColorResourceFromType
import com.fabulouszanna.fabpokedex.core.util.retrieveDrawableFromName
import com.fabulouszanna.fabpokedex.databinding.ItemPokemonBinding
import com.fabulouszanna.fabpokedex.features.pokemon.domain.model.PokemonCard

class PokemonListAdapter(private val glide: RequestManager) :
    RecyclerView.Adapter<PokemonListAdapter.PokemonViewHolder>() {

    private var onCardClicked: (id: String) -> Unit = {}

    fun setOnCardClicked(onCardClicked: (id: String) -> Unit) {
        this.onCardClicked = onCardClicked
    }

    private val diffCallback = object : DiffUtil.ItemCallback<PokemonCard>() {
        override fun areItemsTheSame(
            oldItem: PokemonCard,
            newItem: PokemonCard
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: PokemonCard,
            newItem: PokemonCard
        ): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var pokemonListItems: List<PokemonCard>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder =
        PokemonViewHolder(
            ItemPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = pokemonListItems[position]
        holder.bind(pokemon)
    }

    override fun getItemCount(): Int = pokemonListItems.size

    inner class PokemonViewHolder(private val binding: ItemPokemonBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val context = binding.root.context

        fun bind(pokemon: PokemonCard) {
            val typeColor = retrieveColorResourceFromType(context, pokemon.type)
            binding.apply {
                pokemonName.text = pokemon.name
                val pokemonImg = retrieveDrawableFromName(context, pokemon.imgUrl)
                glide.load(pokemonImg).into(pokemonPic)

                root.setCardBackgroundColor(typeColor)
                root.setOnClickListener {
                    onCardClicked(pokemon.id)
                }
            }
        }
    }
}