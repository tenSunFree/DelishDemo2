package com.example.delishdemo2.common.remote.repository

import com.example.delishdemo2.common.local.model.Recipe

interface RandomRecipesRepository {

    suspend fun getRandomRecipes(
        tags: String?,
        number: Int?
    ): List<Recipe>
}
