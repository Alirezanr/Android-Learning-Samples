package com.example.composeapplication.design_patterns.singleton

import com.example.composeapplication.design_patterns.factory.OutfitFactory

class SingletonSampleRunner {
    fun runSample() {
        val shoppingCardJava = ShoppingCardJava.getInstance()
        shoppingCardJava.addItem("Shirt")
        shoppingCardJava.addItem("Shows")
        shoppingCardJava.getItems().forEach {
            println(it)
        }

        val shoppingCardKotlin = ShoppingCardKotlin.apply {
            val summerOutfit = OutfitFactory.getSummerOutfit()
            val sportOutfit = OutfitFactory.getSportOutfit()
            ShoppingCardKotlin.addItem(summerOutfit)
            ShoppingCardKotlin.addItem(sportOutfit)
            getItems().forEach {
                println(it)
            }
        }
    }
}