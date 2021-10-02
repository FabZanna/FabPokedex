package com.fabulouszanna.fabpokedex.features.pokemon.presentation.pokemon_list.filter_bottom_sheet

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fabulouszanna.fabpokedex.core.util.retrieveColorResourceFromType
import com.fabulouszanna.fabpokedex.databinding.ItemTypeFilterBinding

class TypeFilterAdapter(
    private val types: List<String>,
    private val onTypeClicked: (type: String) -> Unit
) :
    RecyclerView.Adapter<TypeFilterAdapter.TypeFilterViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeFilterViewHolder =
        TypeFilterViewHolder(
            ItemTypeFilterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: TypeFilterViewHolder, position: Int) {
        val item = types[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = types.size

    inner class TypeFilterViewHolder(private val binding: ItemTypeFilterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            val color = retrieveColorResourceFromType(binding.root.context, item)

            binding.type.apply {
                text = item
                backgroundTintList = ColorStateList.valueOf(color)
                setOnClickListener {
                    onTypeClicked(item)
                }
            }
        }
    }
}