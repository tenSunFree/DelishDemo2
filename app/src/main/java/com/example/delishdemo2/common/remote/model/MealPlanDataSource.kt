package com.example.delishdemo2.common.remote.model

interface MealPlanDataSource {
    suspend fun getMealsPlan(): MealsPlan
}
