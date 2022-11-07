package com.example.composeapplication.design_patterns.chain_of_responsibility


class ChainOfResponsibilitySampleRunner {
    fun runSample() {
        val currentOutfit = CurrentSeason().getOutfitForCurrentSeason()
        println(currentOutfit.shirt.style)
    }
}