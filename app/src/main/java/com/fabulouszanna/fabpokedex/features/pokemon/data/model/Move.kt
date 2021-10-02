package com.fabulouszanna.fabpokedex.features.pokemon.data.model

import com.google.gson.annotations.SerializedName

data class Move(
    @SerializedName("learned_by")
    val learnedBy: String,
    val name: String,
    val type: String
)