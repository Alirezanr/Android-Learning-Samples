package com.example.composeapplication.design_patterns.chain_of_responsibility

import com.example.composeapplication.design_patterns.factory.Outfit
import com.example.composeapplication.design_patterns.factory.OutfitFactory

class Summer : Season() {

    override fun getOutfit(): Outfit {
        return if (CurrentSeason.currentSeason == "summer")
            OutfitFactory.getSummerOutfit()
        else
            next()
    }

    override fun next(): Outfit {
        return Fall().getOutfit()
    }
}
