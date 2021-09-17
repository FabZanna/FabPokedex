package com.fabulouszanna.fabpokedex.data.ability

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "abilities")
data class Ability(
    @PrimaryKey
    val id: Int,
    val name: String,
    @ColumnInfo(name = "game_text")
    val gameText: String,
    @ColumnInfo(name = "in_depth_effect")
    val inDepthEffect: String
)
