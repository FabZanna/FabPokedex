package com.fabulouszanna.fabpokedex.features.pokemon.data.db

import androidx.room.Dao
import androidx.room.Query
import com.fabulouszanna.fabpokedex.features.pokemon.data.model.Pokemon
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {
    @Query("SELECT * FROM pokemon")
    fun getAllPokemon(): Flow<List<Pokemon>>

    @Query("SELECT * FROM pokemon WHERE pokemon_id=:id")
    fun getPokemonById(id: String): Flow<Pokemon>
}