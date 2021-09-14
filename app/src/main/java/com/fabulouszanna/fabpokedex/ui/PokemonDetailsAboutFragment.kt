package com.fabulouszanna.fabpokedex.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.fabulouszanna.fabpokedex.data.pokemon.BaseStats
import com.fabulouszanna.fabpokedex.databinding.FragmentPokemonDetailsAboutBinding
import com.fabulouszanna.fabpokedex.databinding.SingleBaseStatBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonDetailsAboutFragment(private val pokemonId: Int) : Fragment() {
    private lateinit var binding: FragmentPokemonDetailsAboutBinding
    private val viewModel: PokemonViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentPokemonDetailsAboutBinding.inflate(inflater, container, false)
        .also { binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        populateView()
    }

    private fun populateView() {
        viewModel.getPokemonById(pokemonId).observe(viewLifecycleOwner) { pokemon ->
            binding.apply {
                pokemonDescription.text = pokemon.pokedexEntry
                populateStat(hp, "HP", pokemon.baseStats.hp)
                populateStat(attack, "Attack", pokemon.baseStats.attack)
                populateStat(defense, "Defense", pokemon.baseStats.defense)
                populateStat(spAttack, "Sp. Atk", pokemon.baseStats.specialAtk)
                populateStat(spDefense, "Sp. Def", pokemon.baseStats.specialDef)
                populateStat(speed, "Speed", pokemon.baseStats.speed)

            }
        }
    }

    private fun populateStat(layout: SingleBaseStatBinding, statName: String, statValue: Int) {
        layout.apply {
            this.statName.text = statName
            this.statValue.text = String.format("%03d", statValue)
            statValueProgress.progress = statValue
        }
    }
}