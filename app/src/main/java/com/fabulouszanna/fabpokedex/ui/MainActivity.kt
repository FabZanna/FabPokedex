package com.fabulouszanna.fabpokedex.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fabulouszanna.fabpokedex.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var pokemonFragmentFactory: PokemonFragmentFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.fragmentFactory = pokemonFragmentFactory
        setContentView(R.layout.activity_main)
    }
}