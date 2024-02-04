package com.example.pokedex.data.sources.remote.dto

import com.google.gson.annotations.SerializedName

data class PokemonDTO(
    @SerializedName("id")
    val id: String,

    @SerializedName("sprites")
    val spritesDTO: SpritesDTO,

    @SerializedName("name")
    val name: String,

    @SerializedName("types")
    val types: List<TypesDTO>,

    @SerializedName("weight")
    val weight: Float,

    @SerializedName("height")
    val height: Float,

    @SerializedName("stats")
    val statsDTO: List<StatsDTO>
)

data class SpritesDTO(
    @SerializedName("other")
    val otherDTO: OtherDTO
)

data class OtherDTO(
    @SerializedName("official-artwork")
    val officialArtworkDTO: OfficialArtworkDTO
)

data class OfficialArtworkDTO(
    @SerializedName("front_default")
    val frontDefault: String
)

data class TypesDTO(
    @SerializedName("slot")
    val slot: Int,
    @SerializedName("type")
    val type: TypesInfoDTO
)

data class TypesInfoDTO(
    @SerializedName("name")
    val name: String
)

data class StatsDTO(
    @SerializedName("base_stat")
    val baseStat: Int,
    @SerializedName("stat")
    val stat: StatsInfoDTO
)

data class StatsInfoDTO(
    @SerializedName("name")
    val name: String
)