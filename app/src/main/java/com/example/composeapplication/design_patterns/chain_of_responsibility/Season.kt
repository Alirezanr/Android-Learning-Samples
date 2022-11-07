package com.example.composeapplication.design_patterns.chain_of_responsibility

import com.example.composeapplication.design_patterns.factory.Outfit

abstract class Season {
    abstract fun getOutfit(): Outfit
    abstract fun next(): Outfit
}