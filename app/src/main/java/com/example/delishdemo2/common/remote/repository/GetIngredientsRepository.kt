package com.example.delishdemo2.common.remote.repository

import com.example.delishdemo2.common.remote.model.GetIngredientsDataSource
import com.example.delishdemo2.common.remote.model.IngredientItem
import javax.inject.Inject

class GetIngredientsRepository @Inject constructor(
    private val getIngredientsDataSource: GetIngredientsDataSource
) : IngredientsRepository {
    override suspend fun getIngredients(): List<IngredientItem> =
        getIngredientsDataSource.getIngredients()
}
