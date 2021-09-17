package com.fabulouszanna.fabpokedex.repositories

import com.fabulouszanna.fabpokedex.data.ability.Ability
import kotlinx.coroutines.flow.Flow

interface AbilityRepository {
    fun getAbilityByName(abilityName: String): Flow<Ability>
}