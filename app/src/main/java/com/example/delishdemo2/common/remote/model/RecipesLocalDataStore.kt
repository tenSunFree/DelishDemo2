package com.example.delishdemo2.common.remote.model

import com.example.delishdemo2.common.local.model.RecipesItem
import kotlinx.coroutines.flow.Flow

interface RecipesLocalDataStore {
    suspend fun saveRecipe(recipesItem: RecipesItem)
    fun getRecipes(): Flow<Result<List<RecipesItem>>>
    suspend fun getRecipeById(recipeId: Int?): RecipesItem?
    suspend fun deleteRecipe(recipeId: Int?)
    suspend fun isRecipeSaved(recipeId: Int?): Boolean
}
