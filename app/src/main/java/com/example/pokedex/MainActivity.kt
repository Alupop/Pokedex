package com.example.pokedex

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pokedex.ui.screens.PokemonListView
import com.example.pokedex.ui.screens.PokemonView
import com.example.pokedex.ui.screens.StartScreen
import com.example.pokedex.ui.theme.PokedexTheme
import com.example.pokedex.ui.viewmodels.PokemonListViewModel
import com.example.pokedex.ui.viewmodels.PokemonViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    val pokemonViewModel: PokemonViewModel by viewModels()
                    val pokemonListViewModel: PokemonListViewModel by viewModels()
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = "StartScreen"
                    ) {
                        composable("StartScreen") {
                            StartScreen(onStartClick = {
                                navController.navigate("PokemonListView")
                            })
                        }

                        composable("PokemonListView") {
                            PokemonListView(pokemonListViewModel) { pokemonName ->
                                navController.navigate("PokemonView/$pokemonName")
                            }
                        }
                        composable("PokemonView/{pokemonName}") { backStackEntry ->
                            val pokemonName = backStackEntry.arguments?.getString("pokemonName")
                            if (pokemonName != null) {
                                PokemonView(pokemonViewModel, pokemonName) {
                                    navController.popBackStack()
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

