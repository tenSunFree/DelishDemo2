package com.example.delishdemo2.common.remote.repository

import com.example.delishdemo2.common.local.model.RecipesItem

interface RecipeInformationRepository {
    suspend fun getRecipeInformation(
        id: Int?
    ): RecipesItem
}
