package com.example.pokedex.ui.utils

import androidx.compose.ui.graphics.Color
import com.example.pokedex.ui.theme.AtkDarkColor
import com.example.pokedex.ui.theme.AtkLightColor
import com.example.pokedex.ui.theme.DefDarkColor
import com.example.pokedex.ui.theme.DefLightColor
import com.example.pokedex.ui.theme.HpDarkColor
import com.example.pokedex.ui.theme.HpLightColor
import com.example.pokedex.ui.theme.S_AtkDarkColor
import com.example.pokedex.ui.theme.S_AtkLightColor
import com.example.pokedex.ui.theme.S_DefDarkColor
import com.example.pokedex.ui.theme.S_DefLightColor
import com.example.pokedex.ui.theme.SpdDarkColor
import com.example.pokedex.ui.theme.SpdLightColor

enum class StatColor(val lightColor: Color, val darkColor: Color) {
    HP(HpLightColor, HpDarkColor),
    ATK(AtkLightColor, AtkDarkColor),
    DEF(DefLightColor, DefDarkColor),
    S_ATK(S_AtkLightColor, S_AtkDarkColor),
    S_DEF(S_DefLightColor, S_DefDarkColor),
    SPD(SpdLightColor, SpdDarkColor)
}