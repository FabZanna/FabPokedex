package com.fabulouszanna.fabpokedex.features.pokemon.presentation.pokemon_details.moves

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.RequestManager
import com.fabulouszanna.fabpokedex.databinding.FragmentPokemonDetailsMovesBinding
import com.fabulouszanna.fabpokedex.features.pokemon.data.model.Pokemon
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PokemonDetailsMovesFragment(private val pokemon: Pokemon) : Fragment() {
    private lateinit var binding: FragmentPokemonDetailsMovesBinding

    @Inject
    lateinit var glide: RequestManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPokemonDetailsMovesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (pokemon.moves != null) {
            setupRecyclerView()
        }
    }

    private fun setupRecyclerView() {
        binding.moves.apply {
            setHasFixedSize(true)
            adapter = PokemonMovesAdapter(pokemon.moves!!, glide)
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(
                DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
            )
        }
    }
}