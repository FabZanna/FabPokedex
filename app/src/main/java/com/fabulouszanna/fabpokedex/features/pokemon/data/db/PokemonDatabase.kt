package com.fabulouszanna.fabpokedex.features.pokemon.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.fabulouszanna.fabpokedex.features.pokemon.data.model.Ability
import com.fabulouszanna.fabpokedex.features.pokemon.data.model.Pokemon

@Database(entities = [Pokemon::class, Ability::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class PokemonDatabase : RoomDatabase() {
    abstract val pokemonDao: PokemonDao
    abstract val abilityDao: AbilityDao

    companion object {
        const val DATABASE_NAME = "pokemon_info.db"
    }
}