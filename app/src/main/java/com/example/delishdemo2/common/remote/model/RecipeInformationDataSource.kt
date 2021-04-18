package com.example.delishdemo2.common.remote.model

import com.example.delishdemo2.common.local.model.Recipe

interface RecipeInformationDataSource {
    suspend fun getRecipeInformation(
        id: Int?
    ): Recipe
}
