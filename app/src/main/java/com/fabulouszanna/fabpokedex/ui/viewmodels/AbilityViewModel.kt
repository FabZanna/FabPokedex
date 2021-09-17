package com.fabulouszanna.fabpokedex.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.fabulouszanna.fabpokedex.repositories.AbilityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AbilityViewModel @Inject constructor(private val repository: AbilityRepository) :
    ViewModel() {
    fun getAbilityByName(abilityName: String) =
        repository.getAbilityByName(abilityName).asLiveData()
}