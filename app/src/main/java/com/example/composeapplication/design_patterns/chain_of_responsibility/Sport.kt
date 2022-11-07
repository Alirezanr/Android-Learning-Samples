package com.example.composeapplication.design_patterns.chain_of_responsibility

import com.example.composeapplication.design_patterns.factory.Outfit
import com.example.composeapplication.design_patterns.factory.OutfitFactory

class Sport : Season() {

    override fun getOutfit(): Outfit {
        return if (CurrentSeason.currentSeason == "sport")
            OutfitFactory.getSportOutfit()
        else
            next()
    }

    override fun next(): Outfit {
        return Generic().getOutfit()
    }
}
