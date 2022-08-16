package com.example.composeapplication.design_patterns.factory

import android.graphics.Color

object OutfitFactory {

    fun getOutfit(style: Style): Outfit = when (style) {
        Style.SUMMER -> Outfit(
            shirt = Shirt(style = "tank top", color = Color.WHITE),
            pants = Pants(style = "shorts", color = Color.BLACK),
            shoes = Shoes(style = "slippers", color = Color.BLACK)
        )
        Style.FALL -> Outfit(
            shirt = Shirt(style = "sweater", color = Color.BLUE),
            pants = Pants(style = "khaki", color = Color.DKGRAY),
            shoes = Shoes(style = "boot", color = Color.BLACK)
        )
        Style.SPORT -> Outfit(
            shirt = Shirt(style = "t-shirt", color = Color.BLUE),
            pants = Pants(style = "training shorts", color = Color.RED),
            shoes = Shoes(style = "sneakers", color = Color.GRAY)
        )
    }


    fun getSummerOutfit() = getOutfit(Style.SUMMER)

    fun getSportOutfit() = getOutfit(Style.SPORT)

    fun getFallOutfit() = getOutfit(Style.FALL)
}