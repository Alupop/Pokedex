package com.example.pokedex.ui.screens

import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import coil.compose.rememberAsyncImagePainter
import com.example.pokedex.domain.models.PokemonModel
import com.example.pokedex.ui.theme.Purple40
import com.example.pokedex.ui.theme.TotalDarkColor
import com.example.pokedex.ui.theme.TotalLightColor
import com.example.pokedex.ui.viewmodels.PokemonViewModel


@Composable
fun PokemonView(pokemonViewModel: PokemonViewModel, pokemonName: String, goBack: () -> Boolean) {
    pokemonViewModel.getPokemon(pokemonName)
    val pokemon by pokemonViewModel.pokemonModel.observeAsState()

    val context = LocalContext.current
    val activity = context as? ComponentActivity

    activity?.let { it ->
        val statusBarColor = pokemon?.let {
            it.types.getOrNull(0)?.type?.name?.let { typeName ->
                pokemonViewModel.getTypeColor(typeName)
            }
        } ?: Color.DarkGray

        it.window.statusBarColor = statusBarColor.toArgb()
    }

    pokemon?.let { pokemon ->
        val capitalizedPokemonName = pokemon.name.replaceFirstChar { it.uppercaseChar() }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.DarkGray),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopBar(pokemon, pokemonViewModel, goBack)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(bottomStart = 50.dp, bottomEnd = 50.dp))
                    .background(pokemon.let {
                        it.types.getOrNull(0)?.type?.name?.let { it1 ->
                            pokemonViewModel.getTypeColor(
                                it1
                            )
                        }
                    } ?: Color.Red),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = pokemon.spritesModel.otherModel.officialArtworkModel.frontDefault),
                    contentDescription = pokemon.id,
                    contentScale = ContentScale.Crop,
                )
            }

            Text(
                text = capitalizedPokemonName,
                fontSize = 40.sp,
                color = Color.White,
                modifier = Modifier.padding(20.dp)
            )

            LazyRow(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                items(pokemon.types.size) { index ->
                    val type = pokemon.types[index]
                    val capitalizedTypeName = type.type.name.replaceFirstChar { it.uppercaseChar() }

                    Box(
                        modifier = Modifier
                            .width(140.dp)
                            .height(40.dp)
                            .clip(RoundedCornerShape(50.dp))
                            .background(pokemonViewModel.getTypeColor(type.type.name)!!),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = capitalizedTypeName,
                            fontSize = 20.sp,
                            color = Color.White,
                            modifier = Modifier.padding(8.dp)
                        )
                    }

                    if (index < pokemon.types.size - 1) {
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth()
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "${pokemon.weight / 10} KG",
                        fontSize = 24.sp,
                        color = Color.White,
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "Weight",
                        fontSize = 16.sp,
                        color = Color.Gray,
                    )
                }

                Spacer(modifier = Modifier.width(50.dp))

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "${pokemon.height / 10} M",
                        fontSize = 24.sp,
                        color = Color.White,
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "Height",
                        fontSize = 16.sp,
                        color = Color.Gray,
                    )
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Base Stats",
                    color = Color.White,
                    fontSize = 24.sp,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            pokemon.statsModels.forEach { stat ->
                Row(
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp, top = 6.dp, bottom = 6.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val shortName = when (stat.stat.name) {
                        "hp" -> "HP"
                        "attack" -> "ATK"
                        "defense" -> "DEF"
                        "special-attack" -> "S. ATK"
                        "special-defense" -> "S. DEF"
                        "speed" -> "SPD"
                        else -> stat.stat.name
                    }

                    Text(
                        text = shortName,
                        color = Color.White,
                        fontSize = 16.sp,
                        modifier = Modifier.width(60.dp)
                    )

                    val statColors = pokemonViewModel.getStatColors(stat.stat.name)

                    if (statColors != null) {
                        val (lightColor, darkColor) = statColors

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(20.dp)
                                .clip(shape = RoundedCornerShape(20.dp))
                                .background(Color.Gray),
                            contentAlignment = Alignment.TopStart
                        ) {
                            Box(
                                modifier = Modifier
                                    .width((255 * stat.baseStat.dp) / 255)
                                    .height(20.dp)
                                    .clip(shape = RoundedCornerShape(20.dp))
                                    .background(
                                        brush = Brush.horizontalGradient(
                                            listOf(
                                                lightColor,
                                                darkColor
                                            )
                                        )
                                    )
                                    .clip(RoundedCornerShape(20.dp)),
                                contentAlignment = Alignment.Center
                            ) {}

                            Row(
                                Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(text = "${stat.baseStat} / 255")
                            }
                        }
                    }
                }
            }

            val totalStats = pokemon.statsModels.sumOf { it.baseStat }

            Row(
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp, top = 6.dp, bottom = 6.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Total",
                    color = Color.White,
                    fontSize = 16.sp,
                    modifier = Modifier.width(60.dp)
                )

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(20.dp)
                        .clip(shape = RoundedCornerShape(20.dp))
                        .background(Color.Gray),
                    contentAlignment = Alignment.TopStart
                ) {
                    Box(
                        modifier = Modifier
                            .width((255 * totalStats.dp) / (255 * 6))
                            .height(20.dp)
                            .clip(shape = RoundedCornerShape(20.dp))
                            .background(
                                brush = Brush.horizontalGradient(
                                    listOf(
                                        TotalLightColor,
                                        TotalDarkColor
                                    )
                                )
                            )
                            .clip(RoundedCornerShape(20.dp)),
                        contentAlignment = Alignment.Center
                    ) {}

                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(text = "$totalStats / ${(255 * 6)}")
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(pokemonModel: PokemonModel, pokemonViewModel: PokemonViewModel, goBack: () -> Boolean) {
    TopAppBar(
        title = {
            Text(
                text = "Pokédex",
                fontSize = 26.sp,
                color = Color.White,
            )
        },
        modifier = Modifier.fillMaxWidth(),
        navigationIcon = {
            IconButton(
                onClick = { goBack() },
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "ArrowBack",
                    tint = Color.White
                )
            }
        },
        colors = topAppBarColors(
            pokemonModel.types.getOrNull(0)?.type?.name?.let { typeName ->
                pokemonViewModel.getTypeColor(typeName) ?: Color.White
            } ?: Color.White
        ),
        actions = {
            Text(
                text = "#${pokemonModel.id}",
                color = Color.White,
                fontSize = 20.sp,
                modifier = Modifier.padding(end = 14.dp)
            )
        }
    )
}