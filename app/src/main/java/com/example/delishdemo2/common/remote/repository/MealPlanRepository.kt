package com.example.delishdemo2.common.remote.repository

import com.example.delishdemo2.common.remote.model.MealsPlan

interface MealPlanRepository {
    suspend fun getMealsPlan(): MealsPlan
}
