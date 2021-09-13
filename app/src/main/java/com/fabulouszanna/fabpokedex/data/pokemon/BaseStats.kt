package com.fabulouszanna.fabpokedex.data.pokemon

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class BaseStats(
    val hp: Int,
    val attack: Int,
    val defense: Int,
    @SerializedName("sp. atk")
    val specialAtk: Int,
    @SerializedName("sp. def")
    val specialDef: Int,
    val speed: Int
)