package com.example.delishdemo2.common.util

import com.example.delishdemo2.common.local.model.RecipesItem
import com.example.delishdemo2.SuspendUseCase
import com.example.delishdemo2.common.remote.repository.RecipeInformationRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetRecipeInformationSuspendUseCase @Inject constructor(
    private val recipeInformationRepository: RecipeInformationRepository,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : SuspendUseCase<Int, RecipesItem>(ioDispatcher) {
    override suspend fun execute(parameters: Int): RecipesItem =
        recipeInformationRepository.getRecipeInformation(parameters)
}
