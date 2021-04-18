package com.example.delishdemo2.common.remote.model

import com.example.delishdemo2.common.local.model.RecipesItem

data class MealsPlan(
    val week: Week? = null
)

data class Week(
    val sunday: DayMeal? = null,
    val saturday: DayMeal? = null,
    val tuesday: DayMeal? = null,
    val wednesday: DayMeal? = null,
    val thursday: DayMeal? = null,
    val friday: DayMeal? = null,
    val monday: DayMeal? = null
)

data class Nutrients(
    val carbohydrates: Double? = null,
    val protein: Double? = null,
    val fat: Double? = null,
    val calories: Double? = null
)

data class DayMeal(
    val meals: List<RecipesItem>? = null,
    val nutrients: Nutrients? = null
)
