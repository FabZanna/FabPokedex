package com.fabulouszanna.fabpokedex.features.pokemon.presentation.pokemon_details.evolution

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.fabulouszanna.fabpokedex.core.util.retrieveDrawableFromName
import com.fabulouszanna.fabpokedex.databinding.EvolutionCardBinding
import com.fabulouszanna.fabpokedex.features.pokemon.data.model.Evolution
import com.fabulouszanna.fabpokedex.features.pokemon.data.model.Pokemon

class PokemonEvolutionsAdapter(private val glide: RequestManager) :
    RecyclerView.Adapter<PokemonEvolutionsAdapter.PokemonEvolutionsViewHolder>() {

    private var onPokemonClicked: (id: String) -> Unit = {}
    fun setOnPokemonClicked(onPokemonClicked: (id: String) -> Unit) {
        this.onPokemonClicked = onPokemonClicked
    }

    private val diffCallback = object : DiffUtil.ItemCallback<Evolution>() {
        override fun areItemsTheSame(oldItem: Evolution, newItem: Evolution): Boolean =
            oldItem.from == newItem.from && oldItem.to == newItem.to && oldItem.how == newItem.how

        override fun areContentsTheSame(oldItem: Evolution, newItem: Evolution): Boolean =
            oldItem == newItem
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var evolutionItems: List<Evolution>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonEvolutionsViewHolder =
        PokemonEvolutionsViewHolder(
            EvolutionCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: PokemonEvolutionsViewHolder, position: Int) {
        val item = evolutionItems[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = evolutionItems.size

    inner class PokemonEvolutionsViewHolder(private val binding: EvolutionCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(evolution: Evolution) {
            binding.apply {
                fromName.text = evolution.from.name
                toName.text = evolution.to.name
                how.text = evolution.how

                loadPics(from, evolution.from.imgUrl)
                loadPics(to, evolution.to.imgUrl)

                from.setOnClickListener {
                    onPokemonClicked(evolution.from.pokemonId)
                }

                to.setOnClickListener {
                    onPokemonClicked(evolution.to.pokemonId)
                }
            }
        }

        private fun loadPics(imageView: ImageView, imgUrl: String) {
            val img = retrieveDrawableFromName(binding.root.context, imgUrl)
            glide.load(img).into(imageView)
        }
    }
}