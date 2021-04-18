package com.example.delishdemo2.common.remote.model

import com.example.delishdemo2.common.remote.DD2Api
import com.example.delishdemo2.common.local.model.Recipes
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetRandomRecipesRemoteDataSource(
    private val api: DD2Api,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : RandomRecipesRemoteDataSource {
    override suspend fun getRandomRecipes(
        tags: String?,
        number: Int?
    ): Recipes =
        withContext(ioDispatcher) {
            api.getRandomRecipes(tags = tags, number = number)
        }
}
