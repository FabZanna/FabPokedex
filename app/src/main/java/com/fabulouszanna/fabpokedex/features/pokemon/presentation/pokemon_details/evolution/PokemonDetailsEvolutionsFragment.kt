package com.fabulouszanna.fabpokedex.features.pokemon.presentation.pokemon_details.evolution

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.RequestManager
import com.fabulouszanna.fabpokedex.databinding.FragmentPokemonDetailsEvolutionsBinding
import com.fabulouszanna.fabpokedex.features.pokemon.data.model.Pokemon
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PokemonDetailsEvolutionsFragment(
    private val pokemon: Pokemon,
    private val onPokemonClicked: (String) -> Unit
) : Fragment() {
    private lateinit var binding: FragmentPokemonDetailsEvolutionsBinding

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
        val evolutionsAdapter = PokemonEvolutionsAdapter(glide, onPokemonClicked)
            .apply { evolutionItems = pokemon.evolutions }
        binding.evolutions.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = evolutionsAdapter
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }
}