package com.example.pokedex.ui.utils

fun ColorStats(): HashMap<String, StatColor> {
    return hashMapOf(
        "hp" to StatColor.HP,
        "attack" to StatColor.ATK,
        "defense" to StatColor.DEF,
        "special-attack" to StatColor.S_ATK,
        "special-defense" to StatColor.S_DEF,
        "speed" to StatColor.SPD
    )
}