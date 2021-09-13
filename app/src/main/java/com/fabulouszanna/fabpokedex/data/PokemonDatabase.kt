package com.fabulouszanna.fabpokedex.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.fabulouszanna.fabpokedex.data.pokemon.Pokemon
import com.fabulouszanna.fabpokedex.other.Converters

@Database(entities = [Pokemon::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class PokemonDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}