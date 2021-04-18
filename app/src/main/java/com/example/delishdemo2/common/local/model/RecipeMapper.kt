package com.example.delishdemo2.common.local.model

import com.example.delishdemo2.common.util.JsonConverter
import javax.inject.Inject

internal interface RecipeMapper {
    fun mapToDataBaseRecipe(recipesItem: RecipesItem): RecipeEntity
    fun mapToDataRecipe(recipeEntity: RecipeEntity): RecipesItem
}

internal class RecipeMapperImpl @Inject constructor(
    private val jsonConverter: JsonConverter
) : RecipeMapper {
    @ExperimentalStdlibApi
    override fun mapToDataBaseRecipe(recipesItem: RecipesItem): RecipeEntity =
        RecipeEntity(
            recipesItem.id ?: 0,
            recipesItem.title ?: "",
            recipesItem.sustainable ?: false,
            recipesItem.glutenFree ?: false,
            recipesItem.veryPopular ?: false,
            recipesItem.vegetarian ?: false,
            recipesItem.dairyFree ?: false,
            recipesItem.veryHealthy ?: false,
            recipesItem.vegan ?: false,
            recipesItem.cheap ?: false,
            recipesItem.spoonacularScore ?: 0.0,
            recipesItem.aggregateLikes ?: 0,
            recipesItem.sourceName ?: "",
            recipesItem.creditsText ?: "",
            recipesItem.readyInMinutes ?: 0,
            recipesItem.image ?: "",
            recipesItem.percentCarbs ?: 0.0,
            recipesItem.percentProtein ?: 0.0,
            recipesItem.percentFat ?: 0.0,
            recipesItem.nutrientsAmount ?: 0.0,
            recipesItem.nutrientsName ?: "",
            jsonConverter.toJson(recipesItem.ingredientOriginalString),
            jsonConverter.toJson(recipesItem.step),
            recipesItem.servings ?: 0
        )

    @ExperimentalStdlibApi
    override fun mapToDataRecipe(recipeEntity: RecipeEntity): RecipesItem =
        RecipesItem(
            recipeEntity.recipeId,
            recipeEntity.sustainable,
            recipeEntity.glutenFree,
            recipeEntity.veryPopular,
            recipeEntity.nutrientsAmount,
            recipeEntity.title,
            recipeEntity.aggregateLikes,
            recipeEntity.creditsText,
            recipeEntity.readyInMinutes,
            recipeEntity.dairyFree,
            recipeEntity.vegetarian,
            recipeEntity.image,
            recipeEntity.veryHealthy,
            recipeEntity.vegan,
            recipeEntity.cheap,
            recipeEntity.spoonScore,
            recipeEntity.sourceName,
            recipeEntity.percentCarbs,
            recipeEntity.percentProtein,
            recipeEntity.percentFat,
            recipeEntity.nutrientsAmount,
            recipeEntity.nutrientsName,
            recipeEntity.servings,
            jsonConverter.fromJson(recipeEntity.steps) ?: emptyList(),
            jsonConverter.fromJson(recipeEntity.ingredientOriginalString) ?: emptyList(),
            true
        )
}
