package com.example.delishdemo2.common.remote.repository

import com.example.delishdemo2.common.remote.model.MealPlanDataSource
import com.example.delishdemo2.common.remote.model.MealsPlan
import javax.inject.Inject

class GetMealPlanRepository @Inject constructor(
    private val mealPlanRemoteDataSource: MealPlanDataSource
) : MealPlanRepository {
    override suspend fun getMealsPlan(): MealsPlan = mealPlanRemoteDataSource.getMealsPlan()
}
