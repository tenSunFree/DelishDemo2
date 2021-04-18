package com.example.delishdemo2.common.remote.model

import com.example.delishdemo2.common.local.model.Recipe

data class SearchItem(
    val results: List<Recipe>,
    val offset: Int,
    val number: Int,
    val totalResults: Int
)
