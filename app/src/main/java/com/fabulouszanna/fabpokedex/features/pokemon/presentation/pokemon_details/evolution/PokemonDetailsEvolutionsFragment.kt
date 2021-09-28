package com.fabulouszanna.fabpokedex.features.pokemon.presentation.pokemon_details.evolution

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.RequestManager
import com.fabulouszanna.fabpokedex.databinding.FragmentPokemonDetailsEvolutionsBinding
import com.fabulouszanna.fabpokedex.features.pokemon.data.model.Pokemon
import com.fabulouszanna.fabpokedex.features.pokemon.presentation.pokemon_details.PokemonDetailsFragmentDirections
import com.fabulouszanna.fabpokedex.features.pokemon.presentation.pokemon_details.PokemonDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PokemonDetailsEvolutionsFragment(private val pokemon: Pokemon) : Fragment() {
    private lateinit var binding: FragmentPokemonDetailsEvolutionsBinding
    private val viewModel: PokemonDetailsViewModel by viewModels()

    @Inject
    lateinit var glide: RequestManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPokemonDetailsEvolutionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val evolutionsAdapter =
            PokemonEvolutionsAdapter(glide).apply { evolutionItems = pokemon.evolutions }
        binding.evolutions.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = evolutionsAdapter
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
        }
        setupNavigation(evolutionsAdapter)
    }

    private fun setupNavigation(adapter: PokemonEvolutionsAdapter) {
        adapter.setOnPokemonClicked { pokemon_id ->
            if (pokemon_id != pokemon.pokemonId) {
                findNavController().navigate(
                    PokemonDetailsFragmentDirections.actionPokemonDetailsFragmentSelf(pokemon_id)
                )
            }
        }
    }

}