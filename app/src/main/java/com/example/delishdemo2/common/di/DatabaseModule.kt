package com.example.delishdemo2.common.di

import android.content.Context
import androidx.room.Room
import com.example.delishdemo2.common.local.DelishDataBase
import com.example.delishdemo2.common.util.JsonConverter
import com.example.delishdemo2.common.util.MIGRATIONS
import com.example.delishdemo2.common.local.model.RecipeMapper
import com.example.delishdemo2.common.local.model.RecipeMapperImpl
import com.example.delishdemo2.common.remote.model.RecipesDatabaseDataStore
import com.example.delishdemo2.common.local.model.RecipesTable
import com.example.delishdemo2.common.local.Constants
import com.example.delishdemo2.common.remote.model.RecipesLocalDataStore
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    @Singleton
    fun provideDelishDatabase(@ApplicationContext context: Context): DelishDataBase {
        return Room.databaseBuilder(
            context,
            DelishDataBase::class.java,
            Constants.DATABASE_NAME
        ).addMigrations(*MIGRATIONS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRecipesTable(delishDataBase: DelishDataBase): RecipesTable {
        return delishDataBase.recipesTable
    }

    @Provides
    @Singleton
    fun provideJsonConverter(moshi: Moshi): JsonConverter = JsonConverter(moshi)

    @Provides
    @Singleton
    fun provideRecipesMapper(jsonConverter: JsonConverter): RecipeMapper {
        return RecipeMapperImpl(jsonConverter)
    }

    @Provides
    @Singleton
    fun provideRecipeDataStore(
        recipesTable: RecipesTable,
        recipeMapper: RecipeMapper,
    ): RecipesLocalDataStore {
        return RecipesDatabaseDataStore(recipesTable, recipeMapper)
    }
}
