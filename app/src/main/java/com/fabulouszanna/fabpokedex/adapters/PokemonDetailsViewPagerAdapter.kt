package com.fabulouszanna.fabpokedex.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.fabulouszanna.fabpokedex.ui.PokemonDetailsAboutFragment
import com.fabulouszanna.fabpokedex.ui.PokemonDetailsEvolutionsFragment

class PokemonDetailsViewPagerAdapter(fragment: Fragment, private val id: Int) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> PokemonDetailsAboutFragment(id)
            1 -> PokemonDetailsEvolutionsFragment(id)
            else -> Fragment()
        }
    }
}