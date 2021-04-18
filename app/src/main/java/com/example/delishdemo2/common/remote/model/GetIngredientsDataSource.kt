package com.example.delishdemo2.common.remote.model

interface GetIngredientsDataSource {
    suspend fun getIngredients(): List<IngredientItem>
}
