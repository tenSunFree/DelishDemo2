package com.example.delishdemo2.common.remote.model

import com.example.delishdemo2.common.local.model.Recipes

interface RandomRecipesRemoteDataSource {

    suspend fun getRandomRecipes(
        tags: String?,
        number: Int?
    ): Recipes
}
