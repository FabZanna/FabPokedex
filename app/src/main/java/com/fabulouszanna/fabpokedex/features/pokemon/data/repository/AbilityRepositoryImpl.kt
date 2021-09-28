package com.fabulouszanna.fabpokedex.features.pokemon.data.repository

import com.fabulouszanna.fabpokedex.features.pokemon.data.model.Ability
import com.fabulouszanna.fabpokedex.features.pokemon.data.db.AbilityDao
import com.fabulouszanna.fabpokedex.features.pokemon.domain.repository.AbilityRepository
import kotlinx.coroutines.flow.Flow

class AbilityRepositoryImpl(private val dao: AbilityDao) : AbilityRepository {
    override fun getAbilityByName(abilityName: String): Flow<Ability> =
        dao.getAbilityByName(abilityName)
}