package com.fabulouszanna.fabpokedex.features.pokemon.domain.repository

import com.fabulouszanna.fabpokedex.features.pokemon.data.model.Ability
import kotlinx.coroutines.flow.Flow

interface AbilityRepository {
    fun getAbilityByName(abilityName: String): Flow<Ability>
}