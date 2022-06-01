package com.example.composeapplication.design_patterns.singleton

class SingletonSampleRunner {
    fun runSample() {
        val shoppingCardJava = ShoppingCardJava.getInstance()
        shoppingCardJava.addItem("Shirt")
        shoppingCardJava.addItem("Shows")
        shoppingCardJava.getItems().forEach {
            println(it)
        }

        val shoppingCardKotlin = ShoppingCardKotlin.apply {
            addItem("Shirt")
            addItem("Shows")
            getItems().forEach {
                println(it)
            }
        }
    }
}