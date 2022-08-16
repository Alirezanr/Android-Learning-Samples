package com.example.composeapplication.design_patterns.decorator

import com.example.composeapplication.design_patterns.factory.OutfitFactory

class DecoratorSampleRunner {
    fun runSample() {
        val summerOutfit = OutfitFactory.getSummerOutfit()
        val outfitWithPromotion = OutfitWithPromotion(summerOutfit, "30%")
        println("Item ${outfitWithPromotion.outfit.shirt.style} is now ${outfitWithPromotion.promotion} off")
        outfitWithPromotion.changePromotion("70%")
        println("Item ${outfitWithPromotion.outfit.shirt.style} is now ${outfitWithPromotion.promotion} off")
    }
}