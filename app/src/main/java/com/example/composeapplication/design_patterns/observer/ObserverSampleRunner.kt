package com.example.composeapplication.design_patterns.observer

import com.example.composeapplication.design_patterns.factory.Outfit
import com.example.composeapplication.design_patterns.factory.OutfitFactory
import com.example.composeapplication.design_patterns.singleton.ShoppingCardKotlin

class ObserverSampleRunner {
    fun runSample() {
        val listener = object : OnShoppingCartChangedListener {
            override fun onOutfitAdded(outfit: Outfit, numItems: Int) {
                updateShoppingCartIcon(numItems)
            }

            override fun onOutfitRemoved(outfit: Outfit, numItems: Int) {
                updateShoppingCartIcon(numItems)
            }
        }

        ShoppingCardKotlin.addOnShoppingCartChangedListener(listener)
        val summerOutfit = OutfitFactory.getSummerOutfit()
        val sportOutfit = OutfitFactory.getSportOutfit()
        ShoppingCardKotlin.addItem(summerOutfit)
        ShoppingCardKotlin.addItem(sportOutfit)
        ShoppingCardKotlin.removeItem(summerOutfit)

    }

    fun updateShoppingCartIcon(numItems: Int) {
        println("Cart items count is $numItems")
    }
}