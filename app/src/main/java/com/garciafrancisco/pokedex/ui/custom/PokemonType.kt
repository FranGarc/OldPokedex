package com.garciafrancisco.pokedex.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.garciafrancisco.pokedex.R


data class Type(
    val key: String,
    @ColorRes val color: Int,
    @StringRes val stringKey: Int,
    @DrawableRes val icon: Int
)

val pokemonTypes = listOf<Type>(
    Type("bug", R.color.TypeBug, R.string.typeBug, R.drawable.ic_type_bug),
    Type("dark", R.color.TypeDark, R.string.typeDark, R.drawable.ic_type_dark),
    Type("dragon", R.color.TypeDragon, R.string.typeDragon, R.drawable.ic_type_dragon),
    Type("electric", R.color.TypeElectric, R.string.typeElectrict, R.drawable.ic_type_electric),
    Type("fighting", R.color.TypeFighting, R.string.typeFigthing, R.drawable.ic_type_fighting),
    Type("fairy", R.color.TypeFairy, R.string.typeFairy, R.drawable.ic_type_fairy),
    Type("fire", R.color.TypeFire, R.string.typeFire, R.drawable.ic_type_fire),
    Type("flying", R.color.TypeFlying, R.string.typeFlying, R.drawable.ic_type_flying),
    Type("ghost", R.color.TypeGhost, R.string.typeGhost, R.drawable.ic_type_ghost),
    Type("grass", R.color.TypeGrass, R.string.typeGrass, R.drawable.ic_type_grass),
    Type("ground", R.color.TypeGround, R.string.typeGround, R.drawable.ic_type_ground),
    Type("ice", R.color.TypeIce, R.string.typeIce, R.drawable.ic_type_ice),
    Type("normal", R.color.TypeNormal, R.string.typeNormal, R.drawable.ic_type_normal),
    Type("poison", R.color.TypePoison, R.string.typePoison, R.drawable.ic_type_poison),
    Type("psychic", R.color.TypePsychic, R.string.typePsychic, R.drawable.ic_type_psychic),
    Type("rock", R.color.TypeRock, R.string.typeRock, R.drawable.ic_type_rock),
    Type("steel", R.color.TypeSteel, R.string.typeSteel, R.drawable.ic_type_steel),
    Type("water", R.color.TypeWater, R.string.typeWater, R.drawable.ic_type_water),
)

fun getType(key: String): Type {
    return pokemonTypes.firstOrNull { it.key == key }
        ?: throw IllegalArgumentException("Invalid type key")
}
