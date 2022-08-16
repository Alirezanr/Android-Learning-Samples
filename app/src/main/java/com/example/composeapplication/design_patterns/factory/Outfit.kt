package com.example.composeapplication.design_patterns.factory

import android.graphics.Color

class Outfit(
    var shirt: Shirt = Shirt("t shirt", Color.WHITE),
    var pants: Pants = Pants("jeans", Color.BLUE),
    var shoes: Shoes = Shoes("boot", Color.GRAY)
)