package com.fabulouszanna.fabpokedex.adapters

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.fabulouszanna.fabpokedex.data.pokemontypes.TypeWeaknessResistanceImmunity
import com.fabulouszanna.fabpokedex.databinding.TypeWeaknessResistanceImmunityItemBinding
import com.fabulouszanna.fabpokedex.other.extractColorResourceFromType
import com.fabulouszanna.fabpokedex.other.retrieveDrawableFromName

class TypeWeaknessResistanceImmunityAdapter(private val glide: RequestManager) :
    RecyclerView.Adapter<TypeWeaknessResistanceImmunityAdapter.TypeWeaknessResistanceImmunityViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<TypeWeaknessResistanceImmunity>() {
        override fun areItemsTheSame(
            oldItem: TypeWeaknessResistanceImmunity,
            newItem: TypeWeaknessResistanceImmunity
        ): Boolean = oldItem.type == newItem.type && oldItem.intensity == newItem.intensity

        override fun areContentsTheSame(
            oldItem: TypeWeaknessResistanceImmunity,
            newItem: TypeWeaknessResistanceImmunity
        ): Boolean = oldItem == newItem
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var typeWeaknessResistanceImmunityItems: List<TypeWeaknessResistanceImmunity>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TypeWeaknessResistanceImmunityViewHolder = TypeWeaknessResistanceImmunityViewHolder(
        TypeWeaknessResistanceImmunityItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: TypeWeaknessResistanceImmunityViewHolder, position: Int) {
        val item = typeWeaknessResistanceImmunityItems[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = typeWeaknessResistanceImmunityItems.size

    inner class TypeWeaknessResistanceImmunityViewHolder(private val binding: TypeWeaknessResistanceImmunityItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private fun formatValue(value: Float): String {
            if (value == 0.5f) {
                return "X \u00BD"
            }
            if (value == 0.25f) {
                return "X \u00BC"
            }
            return "X ${value.toInt()}"
        }

        fun bind(item: TypeWeaknessResistanceImmunity) {
            val img = retrieveDrawableFromName(binding.root.context, item.type.lowercase())
            val color = extractColorResourceFromType(binding.root.context, item.type.lowercase())
            binding.apply {
                root.backgroundTintList = ColorStateList.valueOf(color)
                glide.load(img).into(typeIcon)
                intensity.text = formatValue(item.intensity)
            }
        }
    }
}