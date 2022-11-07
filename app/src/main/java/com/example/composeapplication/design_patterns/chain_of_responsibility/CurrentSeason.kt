package com.example.composeapplication.design_patterns.chain_of_responsibility

class CurrentSeason {

    companion object {
        const val currentSeason: String = "sport"
    }

    fun getOutfitForCurrentSeason() = Summer().getOutfit()
}