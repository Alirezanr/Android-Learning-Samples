package com.example.composeapplication.design_patterns.observer

import com.example.composeapplication.design_patterns.factory.Outfit

interface OnShoppingCartChangedListener {
    fun onOutfitAdded(outfit: Outfit, numItems: Int)
    fun onOutfitRemoved(outfit: Outfit, numItems: Int)
}