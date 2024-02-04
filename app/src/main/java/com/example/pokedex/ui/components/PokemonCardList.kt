package com.example.pokedex.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.pokedex.R
import com.example.pokedex.domain.models.PokemonModel
import java.util.Locale

@Composable
fun PokemonCardList(
    onPokemonSelected: (String) -> Unit,
    pokemonModel: PokemonModel?,
    backgroundColor: Color
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(10.dp)
            .clickable {
                if (pokemonModel != null) {
                    onPokemonSelected(pokemonModel.name)
                }
            },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "#" + pokemonModel?.id,
                color = Color.Black
            )
            AsyncImage(
                model =
                if (pokemonModel?.spritesModel?.otherModel?.officialArtworkModel?.frontDefault.isNullOrEmpty()) R.drawable.pokeball
                else pokemonModel?.spritesModel?.otherModel?.officialArtworkModel?.frontDefault,
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .padding(vertical = 15.dp)
            )
            Text(
                text = pokemonModel?.name?.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.ROOT
                    ) else it.toString()
                }
                    ?: "",
                color = Color.Black
            )
        }
    }
}