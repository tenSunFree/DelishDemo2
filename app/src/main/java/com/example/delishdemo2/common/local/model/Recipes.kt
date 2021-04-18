package com.example.delishdemo2.common.local.model

data class Recipes(
    val recipes: List<Recipe> = emptyList()
)

data class RecipesItem(
    val id: Int? = 1,
    val sustainable: Boolean? = null,
    val glutenFree: Boolean? = null,
    val veryPopular: Boolean? = null,
    val healthScore: Double? = null,
    val title: String? = null,
    val aggregateLikes: Int? = null,
    val creditsText: String? = null,
    val readyInMinutes: Int? = null,
    val dairyFree: Boolean? = null,
    val vegetarian: Boolean? = null,
    val image: String? = null,
    val veryHealthy: Boolean? = null,
    val vegan: Boolean? = null,
    val cheap: Boolean? = null,
    val spoonacularScore: Double? = null,
    val sourceName: String? = null,
    val percentCarbs: Double? = null,
    val percentProtein: Double? = null,
    val percentFat: Double? = null,
    val nutrientsAmount: Double? = 0.0,
    val nutrientsName: String? = "",
    val servings: Int? = 0,
    val step: List<String>? = emptyList(),
    val ingredientOriginalString: List<String>? = emptyList(),
    var saved: Boolean = false
)

data class Recipe(
    val sustainable: Boolean? = null,
    val glutenFree: Boolean? = null,
    val veryPopular: Boolean? = null,
    val healthScore: Double? = null,
    val title: String? = null,
    val aggregateLikes: Int? = null,
    val creditsText: String? = null,
    val readyInMinutes: Int? = null,
    val dairyFree: Boolean? = null,
    val vegetarian: Boolean? = null,
    val id: Int? = 1,
    val image: String? = null,
    val veryHealthy: Boolean? = null,
    val vegan: Boolean? = null,
    val cheap: Boolean? = null,
    val spoonacularScore: Double? = null,
    val sourceName: String? = null,
    val nutrition: Nutrition? = null,
    val servings: Int? = 0,
    val analyzedInstructions: List<AnalyzedInstructionsItem>? = null,
    val extendedIngredients: List<ExtendedIngredientsItem>? = null
)

data class CaloricBreakdown(
    val percentCarbs: Double? = null,
    val percentProtein: Double? = null,
    val percentFat: Double? = null
)

data class NutrientsItem(
    val amount: Double? = null,
    val name: String? = null,
)

data class Nutrition(
    val caloricBreakdown: CaloricBreakdown? = null,
    val nutrients: List<NutrientsItem>? = null
)

data class AnalyzedInstructionsItem(val steps: List<StepsItem>? = null)

data class StepsItem(val step: String? = null)

data class ExtendedIngredientsItem(val originalString: String? = null)

fun Recipe.toUiModel(): RecipesItem =
    RecipesItem(
        id,
        sustainable,
        glutenFree,
        veryPopular,
        healthScore,
        title,
        aggregateLikes,
        creditsText,
        readyInMinutes,
        dairyFree,
        vegetarian,
        image,
        veryHealthy,
        vegan,
        cheap,
        spoonacularScore,
        sourceName,
        nutrition?.caloricBreakdown?.percentCarbs,
        nutrition?.caloricBreakdown?.percentProtein,
        nutrition?.caloricBreakdown?.percentFat,
        nutrition?.nutrients?.nutritionToNutrientsItem()?.amount,
        nutrition?.nutrients?.nutritionToNutrientsItem()?.name,
        servings,
        createStepsList(analyzedInstructions),
        extendedIngredients?.toIngredientUiModel()
    )

fun createStepsList(analyzedInstructions: List<AnalyzedInstructionsItem>?): List<String>? {
    return if (analyzedInstructions.isNullOrEmpty()) {
        emptyList()
    } else {
        analyzedInstructions[0].steps?.toStepsUiModel()
    }
}

fun List<NutrientsItem>.nutritionToNutrientsItem(): NutrientsItem? {
    return this.find {
        it.name?.equals("Calories", true) ?: false
    }
}

fun List<StepsItem>.toStepsUiModel(): List<String> {
    return this.map {
        it.step ?: ""
    }
}

fun List<ExtendedIngredientsItem>.toIngredientUiModel(): List<String> {
    return this.map {
        it.originalString ?: ""
    }
}
