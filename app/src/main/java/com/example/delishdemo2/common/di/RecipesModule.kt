package com.example.delishdemo2.common.di

import com.example.delishdemo2.common.remote.DD2Api
import com.example.delishdemo2.common.remote.model.*
import com.example.delishdemo2.common.remote.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class RecipesModule {

    @Provides
    fun provideRandomRecipesDataSource(api: DD2Api): RandomRecipesRemoteDataSource =
        GetRandomRecipesRemoteDataSource(api)

    @Provides
    fun provideRandomRecipesRepository(
        randomRecipesRemoteDataSource: RandomRecipesRemoteDataSource
    ): RandomRecipesRepository =
        GetRandomRecipesRepository(randomRecipesRemoteDataSource)

    @Provides
    fun provideGetCuisinesDataSource(api: DD2Api): GetCuisinesDataSource =
        GetCuisinesRemoteDataSource(api)

    @Provides
    fun provideCuisinesRepository(
        getCuisinesDataSource: GetCuisinesDataSource
    ): CuisinesRepository =
        GetCuisinesRepository(getCuisinesDataSource)

    @Provides
    fun provideRecipeInformationDataSource(api: DD2Api): RecipeInformationDataSource =
        RecipeInformationRemoteDataSource(api)

    @Provides
    fun provideRecipeInformationRepository(
        recipeInformationDataSource: RecipeInformationDataSource,
        recipesLocalDataStore: RecipesLocalDataStore
    ): RecipeInformationRepository =
        GetRecipeInformationRepository(
            recipeInformationDataSource,
            recipesLocalDataStore
        )

    // @Provides
    // fun provideSearchDataSource(api: DelishApi): SearchDataSource =
    //     SearchRecipesDataSource(api)

    // @Provides
    // fun provideSearchRepository(
    //     rearchDataSource: SearchDataSource
    // ): SearchRepository =
    //     SearchRecipesRepository(rearchDataSource)

    @Provides
    fun provideMealPlanDataSource(api: DD2Api): MealPlanDataSource =
        GetMealPlanRemoteDataSource(api)

    @Provides
    fun provideMealPlanRepository(
        mealPlanDataSource: MealPlanDataSource
    ): MealPlanRepository =
        GetMealPlanRepository(mealPlanDataSource)

    @Provides
    fun provideIngredientsDataSource(api: DD2Api): GetIngredientsDataSource =
        GetIngredientsRemoteDataSource(api)

    @Provides
    fun provideIngredientsRepository(
        getIngredientsDataSource: GetIngredientsDataSource
    ): IngredientsRepository =
        GetIngredientsRepository(getIngredientsDataSource)
}
