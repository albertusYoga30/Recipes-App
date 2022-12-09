package com.example.modernrecipes.data.network

import com.example.modernrecipes.models.FoodRecipe
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface FoodRecipeAPI {
    @GET("/recipes/complexSearch")
    suspend fun getRecipes(
        @QueryMap queries:Map<String,String>
    ):Response<FoodRecipe>
}