package com.example.pokedex

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.pokedex.ui.screens.PokemonView
import com.example.pokedex.ui.theme.PokedexTheme
import com.example.pokedex.ui.viewmodels.PokemonViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var pokemonViewModel: PokemonViewModel
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexTheme {
                /*val navController = rememberNavController()
                val pokemonViewModel: PokemonViewModel by viewModels()*/

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    PokemonView(pokemonViewModel = pokemonViewModel)
                }
            }
        }
    }
}

