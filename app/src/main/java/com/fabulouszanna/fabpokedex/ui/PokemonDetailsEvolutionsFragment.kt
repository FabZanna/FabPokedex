package com.fabulouszanna.fabpokedex.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.fabulouszanna.fabpokedex.adapters.PokemonEvolutionsAdapter
import com.fabulouszanna.fabpokedex.databinding.FragmentPokemonDetailsEvolutionsBinding
import com.fabulouszanna.fabpokedex.ui.viewmodels.PokemonViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonDetailsEvolutionsFragment(private val pokemonId: Int) : Fragment() {
    private lateinit var binding: FragmentPokemonDetailsEvolutionsBinding
    private val viewModel: PokemonViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentPokemonDetailsEvolutionsBinding.inflate(inflater, container, false)
        .also { binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val evolutionsAdapter = PokemonEvolutionsAdapter()
        setupRecyclerView(evolutionsAdapter)
        subscribeToObservers(evolutionsAdapter)
    }

    private fun subscribeToObservers(evolutionsAdapter: PokemonEvolutionsAdapter) {
        viewModel.getPokemonById(pokemonId).observe(viewLifecycleOwner) { pokemon ->
            evolutionsAdapter.evolutionItems = pokemon.evolutions
        }
    }

    private fun setupRecyclerView(evolutionsAdapter: PokemonEvolutionsAdapter) {
        binding.evolutions.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = evolutionsAdapter
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        }
    }

}