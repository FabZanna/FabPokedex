package com.fabulouszanna.fabpokedex.features.pokemon.data.db

import androidx.room.TypeConverter
import com.fabulouszanna.fabpokedex.features.pokemon.data.model.BaseStats
import com.fabulouszanna.fabpokedex.features.pokemon.data.model.Evolution
import com.fabulouszanna.fabpokedex.features.pokemon.data.model.Move
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class Converters {
    @TypeConverter
    fun toListOfStrings(value: String): List<String> {
        val objectType: Type = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, objectType)
    }

    @TypeConverter
    fun fromListOfStrings(value: List<String>): String = Gson().toJson(value)

    @TypeConverter
    fun toBaseStats(value: String): BaseStats {
        val objectType: Type = object : TypeToken<BaseStats>() {}.type
        return Gson().fromJson(value, objectType)
    }

    @TypeConverter
    fun fromBaseStats(value: BaseStats): String = Gson().toJson(value)

    @TypeConverter
    fun toListEvolution(value: String): List<Evolution> {
        val objectType: Type = object : TypeToken<List<Evolution>>() {}.type
        return Gson().fromJson(value, objectType)
    }

    @TypeConverter
    fun fromListEvolution(value: List<Evolution>): String = Gson().toJson(value)

    @TypeConverter
    fun toListMove(value: String?): List<Move>? {
        val objectType: Type = object : TypeToken<List<Move>>() {}.type
        return Gson().fromJson(value, objectType)
    }

    @TypeConverter
    fun fromListMove(value: List<Move>?): String? = Gson().toJson(value)
}