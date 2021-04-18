package com.example.delishdemo2.common.remote.model

import com.example.delishdemo2.common.local.model.RecipeEntity
import com.example.delishdemo2.common.local.model.RecipeMapper
import com.example.delishdemo2.common.local.model.RecipesItem
import com.example.delishdemo2.common.local.model.RecipesTable
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class RecipesDatabaseDataStore @Inject constructor(
    private val recipesTable: RecipesTable,
    private val recipeMapper: RecipeMapper,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : RecipesLocalDataStore {

    override suspend fun saveRecipe(recipesItem: RecipesItem) {
        recipesTable.saveRecipe(
            withContext(ioDispatcher) {
                recipeMapper.mapToDataBaseRecipe(recipesItem)
            }
        )
    }

    override fun getRecipes(): Flow<Result<List<RecipesItem>>> {
        return recipesTable.getRecipes().toListDataRecipeFlow()
    }

    private fun Flow<List<RecipeEntity>>.toListDataRecipeFlow(): Flow<Result<List<RecipesItem>>> {
        return this.map { items ->
            Result.Success(items.toDataRecipes())
        }
    }

    private fun List<RecipeEntity>.toDataRecipes(): List<RecipesItem> {
        return this.map {
            recipeMapper.mapToDataRecipe(it)
        }
    }

    override suspend fun getRecipeById(recipeId: Int?): RecipesItem? {
        val savedRecipe = recipesTable.getRecipe(recipeId)
        return if (savedRecipe != null) {
            recipeMapper.mapToDataRecipe(savedRecipe)
        } else {
            null
        }
    }

    override suspend fun deleteRecipe(recipeId: Int?) = recipesTable.deleteRecipe(recipeId)

    override suspend fun isRecipeSaved(recipeId: Int?) = recipesTable.isRecipeSaved(recipeId)
}
