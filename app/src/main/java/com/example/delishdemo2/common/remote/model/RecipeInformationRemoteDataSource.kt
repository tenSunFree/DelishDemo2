package com.example.delishdemo2.common.remote.model

import com.example.delishdemo2.common.remote.DD2Api
import com.example.delishdemo2.common.local.model.Recipe
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RecipeInformationRemoteDataSource(
    private val api: DD2Api,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : RecipeInformationDataSource {
    override suspend fun getRecipeInformation(id: Int?): Recipe =
        withContext(ioDispatcher) {
            api.getRecipeInformation(id = id)
        }
}
