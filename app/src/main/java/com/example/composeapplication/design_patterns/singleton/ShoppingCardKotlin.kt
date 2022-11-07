package com.example.composeapplication.design_patterns.singleton

import com.example.composeapplication.design_patterns.factory.Outfit
import com.example.composeapplication.design_patterns.observer.OnShoppingCartChangedListener

object ShoppingCardKotlin {
    private val items = mutableListOf<Outfit>()
    private val listeners = ArrayList<OnShoppingCartChangedListener>()
    fun addItem(item: Outfit) {
        items.add(item)
        listeners.forEach { it.onOutfitAdded(item, items.size) }
    }

    fun removeItem(item: Outfit) {
        if (items.contains(item)) {
            items.remove(item)
            listeners.forEach { it.onOutfitRemoved(item, items.size) }
        }
    }

    fun getItems(): List<Outfit> {
        return items
    }

    fun addOnShoppingCartChangedListener(listener: OnShoppingCartChangedListener) {
        listeners.add(listener)
    }

    fun removeOnShoppingCartChangedListener(listener: OnShoppingCartChangedListener) {
        if (listeners.contains(listener))
            listeners.remove(listener)
    }
}