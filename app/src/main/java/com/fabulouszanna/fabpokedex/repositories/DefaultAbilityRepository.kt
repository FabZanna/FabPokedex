package com.fabulouszanna.fabpokedex.repositories

import com.fabulouszanna.fabpokedex.data.AbilityDao
import com.fabulouszanna.fabpokedex.data.ability.Ability
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultAbilityRepository @Inject constructor(private val dao: AbilityDao) : AbilityRepository {
    override fun getAbilityByName(abilityName: String): Flow<Ability> = dao.getAbilityByName(abilityName)
}