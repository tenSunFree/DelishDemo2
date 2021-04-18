package com.example.delishdemo2.common.remote.model

import com.example.delishdemo2.common.remote.DD2Api
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetMealPlanRemoteDataSource @Inject constructor(
    private val api: DD2Api,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : MealPlanDataSource {
    override suspend fun getMealsPlan(): MealsPlan =
        withContext(ioDispatcher) {
            api.getMealsPlan()
        }
}
