package com.example.modernrecipes.models

import com.google.gson.annotations.SerializedName

data class FoodRecipe(
    @SerializedName("result")
    val results: List<Result>
)
