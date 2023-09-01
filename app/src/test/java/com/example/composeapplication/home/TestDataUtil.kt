package com.example.composeapplication.home

import java.util.*


private val random = Random()

fun String.appendRandom() = "$this-${random.nextInt(10000)}"

fun randomInt() = Random().nextInt()

fun testPostDtoData(
    id: Int = randomInt(),
    title: String = "Title-".appendRandom(),
    body: String = "Body-".appendRandom(),
) = PostDto(
    id = id,
    title = title,
    body = body
)

