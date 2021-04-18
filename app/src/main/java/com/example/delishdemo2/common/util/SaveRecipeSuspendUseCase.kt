package com.example.delishdemo2.common.util

import com.example.delishdemo2.common.local.model.RecipesItem
import com.example.delishdemo2.SuspendUseCase
import com.example.delishdemo2.common.remote.model.RecipesLocalDataStore
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class SaveRecipeSuspendUseCase @Inject constructor(
    private val dataStore: RecipesLocalDataStore,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : SuspendUseCase<RecipesItem, Unit>(ioDispatcher) {
    override suspend fun execute(
        parameters: RecipesItem
    ) = dataStore.saveRecipe(parameters)
}
