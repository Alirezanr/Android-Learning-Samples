package com.example.composeapplication.design_patterns.chain_of_responsibility

import com.example.composeapplication.design_patterns.factory.Outfit
import com.example.composeapplication.design_patterns.factory.OutfitFactory

class Fall : Season() {

    override fun getOutfit(): Outfit {
        return if (CurrentSeason.currentSeason == "fall")
            OutfitFactory.getFallOutfit()
        else
            next()
    }

    override fun next(): Outfit {
        return Sport().getOutfit()
    }
}
