package com.example.pokedex

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.pokedex.ui.theme.PokedexTheme
import com.example.pokedex.viewmodels.PokemonViewModel
import com.example.pokedex.views.PokemonView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val infoPokemonViewModel: PokemonViewModel by viewModels()
            PokedexTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    PokemonView(pokemonViewModel = infoPokemonViewModel)
                }
            }
        }
    }
}

