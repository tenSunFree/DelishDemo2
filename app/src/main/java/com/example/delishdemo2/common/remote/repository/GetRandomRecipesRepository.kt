package com.example.delishdemo2.common.remote.repository

import com.example.delishdemo2.common.remote.model.RandomRecipesRemoteDataSource
import com.example.delishdemo2.common.local.model.Recipe

class GetRandomRecipesRepository(
    private val randomRecipesRemoteDataSource: RandomRecipesRemoteDataSource
) : RandomRecipesRepository {
    override suspend fun getRandomRecipes(tags: String?, number: Int?): List<Recipe> =
        randomRecipesRemoteDataSource.getRandomRecipes(tags, number).recipes
}
