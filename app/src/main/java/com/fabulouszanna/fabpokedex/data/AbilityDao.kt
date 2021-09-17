package com.fabulouszanna.fabpokedex.data

import androidx.room.Dao
import androidx.room.Query
import com.fabulouszanna.fabpokedex.data.ability.Ability
import kotlinx.coroutines.flow.Flow

@Dao
interface AbilityDao {
    @Query("SELECT * FROM abilities WHERE name=:abilityName")
    fun getAbilityByName(abilityName: String): Flow<Ability>
}