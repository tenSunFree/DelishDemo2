package com.example.delishdemo2.common.util

import com.example.delishdemo2.common.remote.model.RecipesLocalDataStore
import com.example.delishdemo2.SuspendUseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class DeleteRecipeSuspendUseCase @Inject constructor(
    private val dataStore: RecipesLocalDataStore,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : SuspendUseCase<Int?, Unit>(ioDispatcher) {
    override suspend fun execute(parameters: Int?) = dataStore.deleteRecipe(parameters)
}
