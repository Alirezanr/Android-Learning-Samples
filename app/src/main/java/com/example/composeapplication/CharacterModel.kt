package com.example.composeapplication

import com.google.gson.annotations.SerializedName

data class CharacterModel(
    @SerializedName("name")
    val name: String,
    @SerializedName("house")
    val house: String?,
    @SerializedName("wizard")
    val wizard: Boolean
)