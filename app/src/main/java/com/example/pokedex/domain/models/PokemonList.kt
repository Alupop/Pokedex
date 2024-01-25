package com.example.pokedex.domain.models

data class PokemonList(
    val results: List<PokemonURL>
)

data class PokemonURL(
    val name: String,
    val url: String
)