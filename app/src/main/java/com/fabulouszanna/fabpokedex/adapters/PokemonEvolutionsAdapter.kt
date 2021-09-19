package com.fabulouszanna.fabpokedex.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.fabulouszanna.fabpokedex.data.pokemon.Evolution
import com.fabulouszanna.fabpokedex.databinding.EvolutionCardBinding
import java.time.LocalDate

class PokemonEvolutionsAdapter :
    RecyclerView.Adapter<PokemonEvolutionsAdapter.PokemonEvolutionsViewHolder>() {

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
                fromName.text = evolution.from
                toName.text = evolution.to
                how.text = evolution.how
            }
        }
    }
}