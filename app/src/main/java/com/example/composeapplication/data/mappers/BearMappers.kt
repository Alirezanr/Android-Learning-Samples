package com.example.composeapplication.data.mappers

import com.example.composeapplication.data.local.model.BeerEntity
import com.example.composeapplication.data.remote.model.BeerDto
import com.example.composeapplication.domain.model.Beer

fun BeerDto.toBeerEntity(): BeerEntity =
    BeerEntity(
        id = id,
        name = name,
        tagline = tagline,
        description = description,
        firstBrewed = first_brewed,
        imageUrl = image_url,
    )

fun BeerEntity.toBeer(): Beer =
    Beer(
        id = id,
        name = name,
        tagline = tagline,
        description = description,
        firstBrewed = firstBrewed,
        imageUrl = imageUrl,
    )