package com.example.delishdemo2.common.util

import com.example.delishdemo2.common.remote.repository.CuisinesRepository
import com.example.delishdemo2.common.remote.model.Result
import com.example.delishdemo2.common.remote.model.CuisineItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAvailableCuisinesUseCase @Inject constructor(
    private val cuisinesRepository: CuisinesRepository
) : UseCase<Unit, Flow<Result<List<CuisineItem>>>>() {

    override fun execute(parameters: Unit): Flow<Result<List<CuisineItem>>> =
        flow {
            try {
                val cuisines = cuisinesRepository.getAvailableCuisines()
                emit(Result.Success(cuisines))
            } catch (e: Exception) {
                emit(Result.Error(e))
            }
        }
}
