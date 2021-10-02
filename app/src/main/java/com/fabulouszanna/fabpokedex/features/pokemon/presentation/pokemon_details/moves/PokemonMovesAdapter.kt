package com.fabulouszanna.fabpokedex.features.pokemon.presentation.pokemon_details.moves

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.fabulouszanna.fabpokedex.core.util.retrieveColorResourceFromType
import com.fabulouszanna.fabpokedex.core.util.retrieveDrawableFromName
import com.fabulouszanna.fabpokedex.databinding.ItemMoveBinding
import com.fabulouszanna.fabpokedex.features.pokemon.data.model.*

class PokemonMovesAdapter(
    private val moves: List<Move>,
    private val glide: RequestManager,
    private val onMoveClicked: (String) -> Unit = {}
) :
    RecyclerView.Adapter<PokemonMovesAdapter.PokemonMovesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonMovesViewHolder =
        PokemonMovesViewHolder(
            ItemMoveBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: PokemonMovesViewHolder, position: Int) {
        val move = moves[position]
        holder.bind(move)
    }

    override fun getItemCount(): Int = moves.size

    inner class PokemonMovesViewHolder(private val binding: ItemMoveBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(move: Move) {
            binding.apply {
                moveName.text = move.name
                additionalInfo.text = move.learnedBy

                val typeImg = retrieveDrawableFromName(binding.root.context, move.type.lowercase())
                val color = retrieveColorResourceFromType(binding.root.context, move.type)
                glide.load(typeImg).into(moveType)
                moveTypeBackground.backgroundTintList = ColorStateList.valueOf(color)
            }
        }
    }
}