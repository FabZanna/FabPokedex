package com.fabulouszanna.fabpokedex.features.pokemon.presentation.pokemon_details.ability_bottom_sheet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.fabulouszanna.fabpokedex.features.pokemon.domain.repository.AbilityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AbilityViewModel @Inject constructor(private val repository: AbilityRepository) :
    ViewModel() {
    fun getAbilityByName(abilityName: String) =
        repository.getAbilityByName(abilityName).asLiveData()
}