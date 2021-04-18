package com.example.delishdemo2.message

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.delishdemo2.common.remote.model.Result
import com.example.delishdemo2.common.local.model.RecipesItem
import com.example.delishdemo2.common.remote.model.CuisineItem
import com.example.delishdemo2.common.remote.model.IngredientItem
import com.example.delishdemo2.common.remote.model.data
import com.example.delishdemo2.common.util.GetAvailableCuisinesUseCase
import com.example.delishdemo2.common.util.GetIngredientsUseCase
import com.example.delishdemo2.common.util.GetRandomRecipesUseCase

const val randomRecipesCount = 30

@HiltViewModel
class MessageViewModel @Inject constructor(
    private val getRandomRecipesUseCase: GetRandomRecipesUseCase,
    private val getAvailableCuisinesUseCase: GetAvailableCuisinesUseCase,
    private val getIngredientsUseCase: GetIngredientsUseCase
) : ViewModel() {

    val hasError = MutableStateFlow(false)
    val loading = MutableStateFlow(false)
    private val _state = MutableStateFlow(RecipesViewState())
    val viewState: StateFlow<RecipesViewState>
        get() = _state

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            combine(
                getRandomRecipesUseCase(
                    GetRandomRecipesUseCase.Params.create(
                        null,
                        randomRecipesCount
                    )
                ),
                getIngredientsUseCase(Unit),
                getAvailableCuisinesUseCase(Unit)
            ) { randomRecipes, ingredients, cuisines ->
                hasError.value = cuisines is Result.Error || ingredients is Result.Error
                RecipesViewState(
                    ingredientList = ingredients.data ?: emptyList(),
                    cuisinesList = cuisines.data ?: emptyList(),
                    randomRecipes = randomRecipes.data ?: emptyList()
                )
            }.onStart {
                loading.value = true
            }.catch {
                hasError.value = true
            }.onCompletion {
                loading.value = false
            }.collect {
                _state.value = it
            }
        }
    }

    data class RecipesViewState(
        val ingredientList: List<IngredientItem> = emptyList(),
        val cuisinesList: List<CuisineItem> = emptyList(),
        val randomRecipes: List<RecipesItem> = emptyList()
    )
}
