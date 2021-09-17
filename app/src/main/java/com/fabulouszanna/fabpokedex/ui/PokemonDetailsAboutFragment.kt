package com.fabulouszanna.fabpokedex.ui

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.RequestManager
import com.fabulouszanna.fabpokedex.adapters.TypeWeaknessResistanceImmunityAdapter
import com.fabulouszanna.fabpokedex.data.pokemontypes.WeaknessResistanceImmunityCalculator
import com.fabulouszanna.fabpokedex.databinding.FragmentPokemonDetailsAboutBinding
import com.fabulouszanna.fabpokedex.databinding.SingleBaseStatBinding
import com.fabulouszanna.fabpokedex.other.RecyclerViewGridLayoutSpace
import com.fabulouszanna.fabpokedex.other.extractColorResourceFromType
import com.fabulouszanna.fabpokedex.ui.customviews.AbilityBottomSheetDialogFragment
import com.fabulouszanna.fabpokedex.ui.viewmodels.PokemonViewModel
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PokemonDetailsAboutFragment(private val pokemonId: Int) : Fragment() {
    private lateinit var binding: FragmentPokemonDetailsAboutBinding
    private val viewModel: PokemonViewModel by viewModels()

    @Inject
    lateinit var glide: RequestManager

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
            val color = extractColorResourceFromType(requireContext(), pokemon.types[0])
            val pokemonWeaknessResistanceImmunity = WeaknessResistanceImmunityCalculator(pokemon.types)
            binding.apply {
                pokemonDescription.text = pokemon.pokedexEntry
                populateStat(hp, "HP", pokemon.baseStats.hp, color)
                populateStat(attack, "Attack", pokemon.baseStats.attack, color)
                populateStat(defense, "Defense", pokemon.baseStats.defense, color)
                populateStat(spAttack, "Sp. Atk", pokemon.baseStats.specialAtk, color)
                populateStat(spDefense, "Sp. Def", pokemon.baseStats.specialDef, color)
                populateStat(speed, "Speed", pokemon.baseStats.speed, color)

                populateAbility(firstAbility, pokemon.abilities[0], color)
                if (pokemon.abilities.size > 1) {
                    populateAbility(secondAbility, pokemon.abilities[1], color)
                }
                if (pokemon.hiddenAbility != null) {
                    populateAbility(hiddenAbility, pokemon.hiddenAbility, color)
                }

                populateWeaknesses(pokemonWeaknessResistanceImmunity)
                populateResistances(pokemonWeaknessResistanceImmunity)
                populateImmunities(pokemonWeaknessResistanceImmunity)
            }
        }
    }

    private fun populateStat(
        layout: SingleBaseStatBinding,
        statName: String,
        statValue: Int,
        color: Int
    ) {
        //TODO add max stat to play with progress bar length
        layout.apply {
            this.statName.text = statName
            this.statValue.text = String.format("%03d", statValue)
            statValueProgress.progress = statValue
            statValueProgress.progressTintList = ColorStateList.valueOf(color)
        }
    }

    private fun populateAbility(layout: MaterialButton, abilityName: String, color: Int) {
        layout.apply {
            text = abilityName
            setBackgroundColor(color)
            visibility = View.VISIBLE
            setOnClickListener {
                showAbilityBottomSheet(abilityName, color)
            }
        }
    }

    private fun showAbilityBottomSheet(abilityName: String, color: Int) {
        val abilityBottomSheet = AbilityBottomSheetDialogFragment(abilityName, color)
        abilityBottomSheet.show(requireActivity().supportFragmentManager, null)
    }

    private fun populateWeaknesses(pokemonWeaknessResistanceImmunity: WeaknessResistanceImmunityCalculator) {
        val weaknesses = pokemonWeaknessResistanceImmunity.weaknesses
        val weaknessesAdapter = TypeWeaknessResistanceImmunityAdapter(glide)
        weaknessesAdapter.typeWeaknessResistanceImmunityItems = weaknesses
        binding.typeWeaknesses.apply {
            setHasFixedSize(true)
            adapter = weaknessesAdapter
            addItemDecoration(RecyclerViewGridLayoutSpace(includeEdge = false, spacing = 4))
        }
    }

    private fun populateResistances(pokemonWeaknessResistanceImmunity: WeaknessResistanceImmunityCalculator) {
        val resistances = pokemonWeaknessResistanceImmunity.resistances
        if (resistances.isEmpty()) {
            return
        }
        val weaknessesAdapter = TypeWeaknessResistanceImmunityAdapter(glide)
        weaknessesAdapter.typeWeaknessResistanceImmunityItems = resistances
        binding.typeResistances.apply {
            setHasFixedSize(true)
            adapter = weaknessesAdapter
            addItemDecoration(RecyclerViewGridLayoutSpace(includeEdge = false, spacing = 4))
            visibility = View.VISIBLE
        }
        binding.resistanceTitle.visibility = View.VISIBLE
    }

    private fun populateImmunities(pokemonWeaknessResistanceImmunity: WeaknessResistanceImmunityCalculator) {
        val immunities = pokemonWeaknessResistanceImmunity.immunities
        if (immunities.isEmpty()) {
            return
        }
        val weaknessesAdapter = TypeWeaknessResistanceImmunityAdapter(glide)
        weaknessesAdapter.typeWeaknessResistanceImmunityItems = immunities
        binding.typeImmunities.apply {
            setHasFixedSize(true)
            adapter = weaknessesAdapter
            addItemDecoration(RecyclerViewGridLayoutSpace(includeEdge = false, spacing = 4))
            visibility = View.VISIBLE
        }
        binding.immunityTitle.visibility = View.VISIBLE
    }
}