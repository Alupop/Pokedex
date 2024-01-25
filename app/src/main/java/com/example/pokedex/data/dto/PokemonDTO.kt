package com.example.pokedex.data.dto

import com.google.gson.annotations.SerializedName

data class PokemonDTO(
    @SerializedName("id")
    val id: String,

    @SerializedName("sprites")
    val sprites: SpritesDTO,

    @SerializedName("name")
    val name: String,

    @SerializedName("types")
    val types: List<TypesDTO>,

    @SerializedName("weight")
    val weight: Float,

    @SerializedName("height")
    val height: Float,

    @SerializedName("stats")
    val stats: List<StatsDTO>
)

data class SpritesDTO(
    @SerializedName("other")
    val other: OtherDTO
)

data class OtherDTO(
    @SerializedName("official-artwork")
    val officialArtwork: OfficialArtworkDTO
)

data class OfficialArtworkDTO(
    @SerializedName("front_default")
    val frontDefault: String
)

data class TypesDTO(
    @SerializedName("slot")
    val slot: Int,
    @SerializedName("type")
    val type: TypeInfoDTO
)

data class TypeInfoDTO(
    @SerializedName("name")
    val name: String
)

data class StatsDTO(
    @SerializedName("base_stat")
    val baseStat: Int,
    @SerializedName("stat")
    val stat: StatInfoDTO
)

data class StatInfoDTO(
    @SerializedName("name")
    val name: String
)