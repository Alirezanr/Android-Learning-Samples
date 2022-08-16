package com.example.composeapplication.design_patterns.decorator

import com.example.composeapplication.design_patterns.factory.Outfit

class OutfitWithPromotion(
    val outfit: Outfit,
    promotion: String
) {
    var promotion: String = promotion
        private set


    fun changePromotion(newPromotion: String) {
        promotion = newPromotion
    }
}