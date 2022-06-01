package com.example.composeapplication.design_patterns.singleton

object ShoppingCardKotlin {
    private val items = mutableListOf<String>()
    fun addItem(item: String) {
        items.add(item)
    }
    fun getItems(): List<String> {
        return items
    }
}