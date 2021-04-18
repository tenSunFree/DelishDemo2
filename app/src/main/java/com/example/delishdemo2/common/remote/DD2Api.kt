package com.example.delishdemo2.common.remote

import com.example.delishdemo2.*
import com.example.delishdemo2.common.local.model.Recipe
import com.example.delishdemo2.common.local.model.Recipes
import com.example.delishdemo2.common.remote.model.CuisineItem
import com.example.delishdemo2.common.remote.model.IngredientItem
import com.example.delishdemo2.common.remote.model.MealsPlan
import com.example.delishdemo2.common.remote.model.SearchItem
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Path

interface DD2Api {

    @GET("recipes/random")
    suspend fun getRandomRecipes(
        @Query("apiKey") apiKey: String = BuildConfig.SPOONACULAR_KEY,
        @Query("tags") tags: String?,
        @Query("number") number: Int?
    ): Recipes

    @GET(BuildConfig.CUISINES_DATA_URL)
    suspend fun getAvailableCuisines(): List<CuisineItem>

    @GET(BuildConfig.INGREDIENTS_DATA_URL)
    suspend fun getIngredients(): List<IngredientItem>

    @GET("recipes/{id}/information")
    suspend fun getRecipeInformation(
        @Path("id") id: Int?,
        @Query("apiKey") apiKey: String = BuildConfig.SPOONACULAR_KEY,
        @Query("includeNutrition") includeNutrition: Boolean? = true
    ): Recipe

    @GET("/recipes/complexSearch")
    suspend fun searchRecipes(
        @Query("apiKey") apiKey: String = BuildConfig.SPOONACULAR_KEY,
        @Query("query") query: String?,
        @Query("cuisine") cuisine: String?,
        @Query("addRecipeInformation") addRecipeInformation: Boolean?,
        @Query("number") number: Int?,
        @Query("offset") offset: Int
    ): SearchItem

    @GET("/mealplanner/generate")
    suspend fun getMealsPlan(
        @Query("apiKey") apiKey: String = BuildConfig.SPOONACULAR_KEY,
        @Query("timeFrame") timeFrame: String = "week"
    ): MealsPlan
}
