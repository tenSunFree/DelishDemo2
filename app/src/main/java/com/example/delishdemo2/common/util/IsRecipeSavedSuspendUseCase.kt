package com.example.delishdemo2.common.util

import com.example.delishdemo2.common.remote.model.RecipesLocalDataStore
import com.example.delishdemo2.SuspendUseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class IsRecipeSavedSuspendUseCase @Inject constructor(
    private val dataStore: RecipesLocalDataStore,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : SuspendUseCase<Int?, Boolean>(ioDispatcher) {

    override suspend fun execute(parameters: Int?): Boolean = dataStore.isRecipeSaved(parameters)
}
