package com.example.delishdemo2.common.remote.repository

import com.example.delishdemo2.common.remote.model.IngredientItem

interface IngredientsRepository {
    suspend fun getIngredients(): List<IngredientItem>
}
