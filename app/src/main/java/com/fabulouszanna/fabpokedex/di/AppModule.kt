package com.fabulouszanna.fabpokedex.di

import android.content.Context
import androidx.room.Room
import com.fabulouszanna.fabpokedex.data.PokemonDatabase
import com.fabulouszanna.fabpokedex.other.Constants.POKEMON_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, PokemonDatabase::class.java, POKEMON_DATABASE)
        .createFromAsset("database/$POKEMON_DATABASE")
        .fallbackToDestructiveMigration()
        .build()
}