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
import com.example.pokedex.data.repositories.PokemonRepository
import com.example.pokedex.data.repositories.PokemonRepositoryImpl
import com.example.pokedex.ui.theme.PokedexTheme
import com.example.pokedex.ui.viewmodels.PokemonViewModelImpl
import com.example.pokedex.ui.screens.PokemonView
import com.example.pokedex.ui.viewmodels.PokemonViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var pokemonRepository: PokemonRepository
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val pokemonViewModel: PokemonViewModel by viewModels()
            PokedexTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    PokemonView(pokemonViewModel = PokemonViewModel)
                }
            }
        }
    }
}

