package com.example.composeapplication.design_patterns.factory

interface Clothing


open class Pants(var style: String, var color: Int): Clothing
open class Shirt(var style: String, var color: Int): Clothing
class Shoes(var style: String, var color: Int): Clothing