package com.fabulouszanna.fabpokedex.data.pokemon

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon")
data class Pokemon(
    @PrimaryKey
    val id: Int,
    val pokemon_id: String,
    val name: String,
    val abilities: List<String>,
    @Embedded
    val base_stats: BaseStats,
    @Embedded
    val evolutions: List<Evolution>,
    val height: String,
    val hidden_ability: String?,
    val img_url: String,
    val pokedex_entry: String,
    val species: String,
    val types: List<String>,
    val weight: String,
    @Embedded
    val moves: List<Move>?,
)