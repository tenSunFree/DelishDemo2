package com.example.delishdemo2.common.local.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
internal interface RecipesTable {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveRecipe(recipe: RecipeEntity)

    @Query(
        """
            SELECT * FROM ${RecipeEntity.Schema.TABLE_NAME}
            """
    )
    fun getRecipes(): Flow<List<RecipeEntity>>

    @Query(
        """
        SELECT * FROM ${RecipeEntity.Schema.TABLE_NAME} 
        WHERE ${RecipeEntity.Schema.RECIPE_ID} = :recipeId
        """
    )
    suspend fun getRecipe(recipeId: Int?): RecipeEntity?

    @Query(
        """
        DELETE FROM ${RecipeEntity.Schema.TABLE_NAME} 
        WHERE ${RecipeEntity.Schema.RECIPE_ID} = :recipeId
        """
    )
    suspend fun deleteRecipe(recipeId: Int?)

    @Query(
        """
        SELECT COUNT(*) FROM ${RecipeEntity.Schema.TABLE_NAME} 
        WHERE ${RecipeEntity.Schema.RECIPE_ID} = :recipeId
        """
    )
    suspend fun isRecipeSaved(recipeId: Int?): Boolean
}
