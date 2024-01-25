package com.example.pokedex.domain.models

data class Pokemon(
    val id: String,

    val sprites: Sprites,

    val name: String,

    val types: List<Types>,

    val weight: Float,

    val height: Float,

    val stats: List<Stat>
)

data class Sprites(
    val other: Other
)

data class Other(
    val officialArtwork: OfficialArtwork
)

data class OfficialArtwork(
    val frontDefault: String
)

data class Types(
    val slot: Int,
    val type: TypeInfo
)

data class TypeInfo(
    val name: String
)

data class Stat(
    val baseStat: Int,
    val stat: StatInfo
)

data class StatInfo(
    val name: String
)