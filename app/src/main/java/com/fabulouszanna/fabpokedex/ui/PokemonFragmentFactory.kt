package com.fabulouszanna.fabpokedex.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.fabulouszanna.fabpokedex.adapters.PokemonListAdapter
import javax.inject.Inject

class PokemonFragmentFactory @Inject constructor(private val pokemonListAdapter: PokemonListAdapter) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className) {
            PokemonListFragment::class.java.name -> PokemonListFragment(pokemonListAdapter)
            else -> super.instantiate(classLoader, className)
        }
    }
}