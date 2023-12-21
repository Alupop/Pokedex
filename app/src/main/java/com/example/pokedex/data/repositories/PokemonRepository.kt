package com.example.pokedex.data.repositories

import com.example.pokedex.data.models.Pokemon
import com.example.pokedex.data.models.StatColor
import com.example.pokedex.data.models.TypeColor

interface PokemonRepository {
    fun getPokemon(): Pokemon
    fun colorTypes(): HashMap<String, TypeColor>
    fun statsTypes(): HashMap<String, StatColor>
}