package com.example.delishdemo2.common.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.delishdemo2.common.local.model.RecipeEntity
import com.example.delishdemo2.common.local.model.RecipesTable

@Database(
    entities = [
        RecipeEntity::class
    ],
    version = Constants.VERSION
)
internal abstract class DelishDataBase : RoomDatabase() {
    abstract val recipesTable: RecipesTable
}
