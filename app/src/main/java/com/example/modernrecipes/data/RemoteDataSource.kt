package com.example.modernrecipes.data

import com.example.modernrecipes.data.network.FoodRecipeAPI
import com.example.modernrecipes.models.FoodRecipe
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val foodRecipeAPI: FoodRecipeAPI
) {
    suspend fun getRecipes(queries: Map<String, String>): Response<FoodRecipe> {
        return foodRecipeAPI.getRecipes(queries)
    }
}