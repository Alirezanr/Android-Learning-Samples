package com.example.composeapplication.design_patterns.singleton

fun main() {
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