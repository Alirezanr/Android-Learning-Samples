package com.example.composeapplication.design_patterns.factory

class FactorySampleRunner {
    fun runSample() {
        //first way
        val summerOutfit = OutfitFactory.getOutfit(Style.SUMMER)
        println("summer shirt style = ${summerOutfit.shirt.style}")
        //second way
        val fallOutfit = OutfitFactory.getFallOutfit()
        println("fall shirt style = ${fallOutfit.shirt.style}")
    }
}