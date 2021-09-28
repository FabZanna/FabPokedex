package com.fabulouszanna.fabpokedex.features.pokemon.presentation.pokemon_details

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.fabulouszanna.fabpokedex.features.pokemon.data.model.Pokemon
import com.fabulouszanna.fabpokedex.features.pokemon.presentation.pokemon_details.about.PokemonDetailsAboutFragment
import com.fabulouszanna.fabpokedex.features.pokemon.presentation.pokemon_details.evolution.PokemonDetailsEvolutionsFragment

class PokemonDetailsViewPagerAdapter(fragment: Fragment, private val pokemon: Pokemon) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> PokemonDetailsAboutFragment(pokemon)
            1 -> PokemonDetailsEvolutionsFragment(pokemon)
            else -> Fragment()
        }
    }
}