package com.example.pokedex.data.models

import androidx.compose.ui.graphics.Color
import com.example.pokedex.ui.theme.AtkColor
import com.example.pokedex.ui.theme.DefColor
import com.example.pokedex.ui.theme.HpColor
import com.example.pokedex.ui.theme.S_AtkColor
import com.example.pokedex.ui.theme.S_DefColor
import com.example.pokedex.ui.theme.SpdColor

enum class StatColor(val color: Color) {
    HP(HpColor),
    ATK(AtkColor),
    DEF(DefColor),
    S_ATK(S_AtkColor),
    S_DEF(S_DefColor),
    SPD(SpdColor)
}