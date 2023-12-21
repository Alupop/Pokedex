package com.example.pokedex.data.models

import androidx.compose.ui.graphics.Color
import com.example.pokedex.ui.theme.BugColor
import com.example.pokedex.ui.theme.DarkColor
import com.example.pokedex.ui.theme.DragonColor
import com.example.pokedex.ui.theme.ElectricColor
import com.example.pokedex.ui.theme.FairyColor
import com.example.pokedex.ui.theme.FightingColor
import com.example.pokedex.ui.theme.FireColor
import com.example.pokedex.ui.theme.FlyingColor
import com.example.pokedex.ui.theme.GhostColor
import com.example.pokedex.ui.theme.GrassColor
import com.example.pokedex.ui.theme.GroundColor
import com.example.pokedex.ui.theme.IceColor
import com.example.pokedex.ui.theme.NormalColor
import com.example.pokedex.ui.theme.PoisonColor
import com.example.pokedex.ui.theme.PsychicColor
import com.example.pokedex.ui.theme.RockColor
import com.example.pokedex.ui.theme.SteelColor
import com.example.pokedex.ui.theme.WaterColor

enum class TypeColor(val color: Color) {
    NORMAL(NormalColor),
    FIRE(FireColor),
    WATER(WaterColor),
    GRASS(GrassColor),
    ELECTRIC(ElectricColor),
    ICE(IceColor),
    FIGHTING(FightingColor),
    POISON(PoisonColor),
    GROUND(GroundColor),
    FLYING(FlyingColor),
    PSYCHIC(PsychicColor),
    BUG(BugColor),
    ROCK(RockColor),
    GHOST(GhostColor),
    DRAGON(DragonColor),
    DARK(DarkColor),
    STEEL(SteelColor),
    FAIRY(FairyColor)
}