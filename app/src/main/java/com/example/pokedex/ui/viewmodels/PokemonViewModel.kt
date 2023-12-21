package com.example.pokedex.ui.viewmodels

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import com.example.pokedex.data.models.Pokemon

interface PokemonViewModel {
    val pokemon: LiveData<Pokemon>
    fun getTypeColor(type: String): Color?
    fun getStatColor(type: String): Color?
}