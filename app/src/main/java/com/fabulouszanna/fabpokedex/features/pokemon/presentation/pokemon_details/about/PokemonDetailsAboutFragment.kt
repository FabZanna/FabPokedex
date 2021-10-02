package com.fabulouszanna.fabpokedex.features.pokemon.presentation.pokemon_details.about

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.RequestManager
import com.fabulouszanna.fabpokedex.core.util.RecyclerViewGridLayoutSpace
import com.fabulouszanna.fabpokedex.core.util.retrieveColorResourceFromType
import com.fabulouszanna.fabpokedex.databinding.FragmentPokemonDetailsAboutBinding
import com.fabulouszanna.fabpokedex.databinding.ItemBaseStatBinding
import com.fabulouszanna.fabpokedex.features.pokemon.data.mapper.WeaknessResistanceImmunityCalculator
import com.fabulouszanna.fabpokedex.features.pokemon.data.model.Pokemon
import com.fabulouszanna.fabpokedex.features.pokemon.presentation.pokemon_details.ability_bottom_sheet.AbilityBottomSheetDialogFragment
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PokemonDetailsAboutFragment(private val pokemon: Pokemon) : Fragment() {
    private lateinit var binding: FragmentPokemonDetailsAboutBinding

    @Inject
    lateinit var glide: RequestManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPokemonDetailsAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        populateView()
    }

    private fun populateView() {
        val color = retrieveColorResourceFromType(requireContext(), pokemon.types[0])
        val pokemonWeaknessResistanceImmunity =
            WeaknessResistanceImmunityCalculator(pokemon.types)
        val maxStatValue = pokemon.baseStats.maxStat
        binding.apply {
            pokemonDescription.text = pokemon.pokedexEntry
            populateStat(hp, "HP", pokemon.baseStats.hp, maxStatValue, color)
            populateStat(attack, "Attack", pokemon.baseStats.attack, maxStatValue, color)
            populateStat(defense, "Defense", pokemon.baseStats.defense, maxStatValue, color)
            populateStat(spAttack, "Sp. Atk", pokemon.baseStats.specialAtk, maxStatValue, color)
            populateStat(
                spDefense,
                "Sp. Def",
                pokemon.baseStats.specialDef,
                maxStatValue,
                color
            )
            populateStat(speed, "Speed", pokemon.baseStats.speed, maxStatValue, color)

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

    private fun populateStat(
        layout: ItemBaseStatBinding,
        statName: String,
        statValue: Int,
        maxStatValue: Int,
        color: Int
    ) {
        layout.apply {
            this.statName.text = statName
            this.statValue.text = String.format("%03d", statValue)
            statValueProgress.max = maxStatValue + 10
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