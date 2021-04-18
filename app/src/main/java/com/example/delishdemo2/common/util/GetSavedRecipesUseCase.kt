package com.example.delishdemo2.common.util

import com.example.delishdemo2.common.local.model.RecipesItem
import com.example.delishdemo2.common.remote.model.RecipesLocalDataStore
import com.example.delishdemo2.common.remote.model.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSavedRecipesUseCase @Inject constructor(
    private val dataStore: RecipesLocalDataStore,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : FlowUseCase<Unit, List<RecipesItem>>(ioDispatcher) {
    override fun execute(parameters: Unit): Flow<Result<List<RecipesItem>>> =
        dataStore.getRecipes()
}
