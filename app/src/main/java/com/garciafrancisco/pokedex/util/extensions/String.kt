package com.garciafrancisco.pokedex.util.extensions

import java.util.*

fun String.pokeCapitalize():String{
    return this.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(
            Locale.ROOT
        ) else it.toString()
    }
}