package com.example.composeapplication.design_patterns.chain_of_responsibility

import com.example.composeapplication.design_patterns.factory.Outfit
import com.example.composeapplication.design_patterns.factory.OutfitFactory

class Generic : Season() {

    override fun getOutfit(): Outfit = Outfit()

    override fun next(): Outfit = getOutfit()
}
