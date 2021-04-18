package com.example.delishdemo2.common.util

import com.example.delishdemo2.common.local.model.RecipesItem
import com.example.delishdemo2.common.local.model.toUiModel
import com.example.delishdemo2.common.remote.model.Result
import com.example.delishdemo2.common.remote.repository.RandomRecipesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

const val defaultRecipesNumber = 10

class GetRandomRecipesUseCase @Inject constructor(
    private val randomRecipesRepository: RandomRecipesRepository
) : UseCase<GetRandomRecipesUseCase.Params, Flow<Result<List<RecipesItem>>>>() {

    override fun execute(parameters: Params): Flow<Result<List<RecipesItem>>> =
        flow {
            try {
                val randomRecipes = randomRecipesRepository.getRandomRecipes(
                    parameters.tags, parameters.number
                ).map {
                    it.toUiModel()
                }
                emit(Result.Success(randomRecipes))
            } catch (e: Exception) {
                emit(Result.Error(e))
            }
        }

    class Params private constructor(
        val tags: String?,
        val number: Int? = defaultRecipesNumber

    ) {

        companion object {
            @JvmStatic
            fun create(tags: String?, number: Int?): Params {
                return Params(tags, number)
            }
        }
    }
}
