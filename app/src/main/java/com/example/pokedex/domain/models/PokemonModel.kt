package com.example.pokedex.domain.models

data class PokemonModel(
    val id: String,
    val spritesModel: SpritesModel,
    val name: String,
    val types: List<TypesModel>,
    val weight: Float,
    val height: Float,
    val statsModels: List<StatsModel>
)

data class SpritesModel(
    val otherModel: OtherModel
)

data class OtherModel(
    val officialArtworkModel: OfficialArtworkModel
)

data class OfficialArtworkModel(
    val frontDefault: String
)

data class TypesModel(
    val slot: Int,
    val type: TypesInfoModel
)

data class TypesInfoModel(
    val name: String
)

data class StatsModel(
    val baseStat: Int,
    val stat: StatsInfoModel
)

data class StatsInfoModel(
    val name: String
)