package com.example.pokedex.domain.repositories

import com.example.pokedex.data.models.Pokemon
import com.example.pokedex.ui.utils.StatColor
import com.example.pokedex.ui.utils.TypeColor

interface IPokemonRepository {
    fun getPokemon(): Pokemon
    fun colorTypes(): HashMap<String, TypeColor>
    fun statsTypes(): HashMap<String, StatColor>
}