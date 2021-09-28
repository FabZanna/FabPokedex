package com.fabulouszanna.fabpokedex.features.pokemon.data.db

import androidx.room.Dao
import androidx.room.Query
import com.fabulouszanna.fabpokedex.features.pokemon.data.model.Ability
import kotlinx.coroutines.flow.Flow

@Dao
interface AbilityDao {
    @Query("SELECT * FROM abilities WHERE name=:abilityName")
    fun getAbilityByName(abilityName: String): Flow<Ability>
}