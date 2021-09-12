package com.fabulouszanna.fabpokedex.other

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class Converters {
    @TypeConverter
    fun toListOfStrings(value: String) : List<String> {
        val objectType: Type = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, objectType)
    }

    @TypeConverter
    fun fromListOfStrings(value: List<String>): String = Gson().toJson(value)
}