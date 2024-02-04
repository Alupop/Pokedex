package com.example.pokedex.ui.screens

import android.annotation.SuppressLint
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.pokedex.R
import com.example.pokedex.domain.models.PokemonModel
import com.example.pokedex.ui.components.PokemonCardList
import com.example.pokedex.ui.viewmodels.PokemonListViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(DelicateCoroutinesApi::class, ExperimentalMaterial3Api::class)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun PokemonListView(
    pokemonListViewModel: PokemonListViewModel,
    onPokemonSelected: (String) -> Unit
) {
    var searchText by remember { mutableStateOf("") }

    val context = LocalContext.current
    val activity = context as? ComponentActivity
    activity?.let {
        it.window.statusBarColor = Color.DarkGray.toArgb()
    }

    val lazyGridState = rememberLazyGridState()

    LaunchedEffect(lazyGridState.layoutInfo.visibleItemsInfo) {
        val lastVisibleItemIndex = lazyGridState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
        val totalPokemons = pokemonListViewModel.pokemonModelListWithInfo.value?.size ?: 0

        if (lastVisibleItemIndex != null && lastVisibleItemIndex >= totalPokemons - 1 && !pokemonListViewModel.isLoading) {
            pokemonListViewModel.getPokemonList {}
        }
    }

    val pokemonListWithInfo by pokemonListViewModel.pokemonModelListWithInfo.observeAsState(
        emptyList()
    )
    val pokemonFilteredList by pokemonListViewModel.pokemonModelFilteredList.observeAsState(
        emptyList()
    )

    var isLoadingScreen by remember { mutableStateOf(true) }

    if (pokemonListWithInfo.isNotEmpty()) {
        GlobalScope.launch {
            delay(3000)
            isLoadingScreen = false
        }
    }

    LaunchedEffect(searchText) {
        delay(200)
        pokemonListViewModel.performSearch(searchText)
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        if (isLoadingScreen) {
            LoadingScreen()
        } else {
            PokemonListContent(
                pokemonListViewModel,
                searchText = searchText,
                onSearchTextChanged = { searchText = it },
                lazyGridState = lazyGridState,
                pokemonListWithInfo = pokemonListWithInfo,
                pokemonFilteredList = pokemonFilteredList,
                onPokemonSelected = onPokemonSelected
            )
        }
    }
}

@Composable
private fun LoadingScreen() {
    Image(
        painter = painterResource(id = R.drawable.loading),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Loading Pokédex...",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )
        CircularProgressIndicator(
            color = Color.White,
            modifier = Modifier
                .width(50.dp)
                .height(50.dp)
        )
    }
}

@Composable
private fun PokemonListContent(
    pokemonListViewModel: PokemonListViewModel,
    searchText: String,
    onSearchTextChanged: (String) -> Unit,
    lazyGridState: LazyGridState,
    pokemonListWithInfo: List<PokemonModel?>,
    pokemonFilteredList: List<PokemonModel?>,
    onPokemonSelected: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = searchText,
                    onValueChange = {
                        onSearchTextChanged(it)
                    },
                    label = {
                        Text(
                            "Search",
                            style = TextStyle(color = Color.White)
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.DarkGray,
                        unfocusedContainerColor = Color.DarkGray,
                        cursorColor = Color.White,
                        focusedIndicatorColor = Color.White,
                        unfocusedIndicatorColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedTextColor = Color.White
                    ),
                )
            }
            LazyVerticalGrid(
                state = lazyGridState,
                columns = GridCells.Fixed(2)
            ) {
                val itemsList =
                    if (searchText.isEmpty()) pokemonListWithInfo else pokemonFilteredList
                items(itemsList) { pokemon ->
                    val backgroundColor =
                        pokemon?.types?.getOrNull(0)?.type?.name?.let { typeName ->
                            pokemonListViewModel.getTypeColor(typeName) ?: Color.Gray
                        } ?: Color.Gray

                    PokemonCardList(onPokemonSelected, pokemon, backgroundColor)
                }

            }
        }
    }
}