package com.example.delishdemo2.common.remote.repository

import com.example.delishdemo2.common.remote.model.RecipeInformationDataSource
import com.example.delishdemo2.common.local.model.RecipesItem
import com.example.delishdemo2.common.remote.model.RecipesLocalDataStore
import com.example.delishdemo2.common.local.model.toUiModel

class GetRecipeInformationRepository(
    private val recipeInformationDataSource: RecipeInformationDataSource,
    private val recipesLocalDataStore: RecipesLocalDataStore
) : RecipeInformationRepository {
    override suspend fun getRecipeInformation(recipeId: Int?): RecipesItem {
        return recipesLocalDataStore.getRecipeById(recipeId)
            ?: recipeInformationDataSource.getRecipeInformation(recipeId).toUiModel()
    }
}
