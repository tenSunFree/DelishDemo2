package com.example.delishdemo2.common.util

import com.example.delishdemo2.common.remote.model.IngredientItem
import com.example.delishdemo2.common.remote.repository.IngredientsRepository
import com.example.delishdemo2.common.remote.model.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetIngredientsUseCase @Inject constructor(
    private val ingredientsRepository: IngredientsRepository
) : UseCase<Unit, Flow<Result<List<IngredientItem>>>>() {

    override fun execute(parameters: Unit): Flow<Result<List<IngredientItem>>> =
        flow {
            try {
                val ingredients = ingredientsRepository.getIngredients()
                emit(Result.Success(ingredients))
            } catch (e: Exception) {
                emit(Result.Error(e))
            }
        }
}
