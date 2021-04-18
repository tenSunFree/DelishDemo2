package com.example.delishdemo2.common.remote.model

import com.example.delishdemo2.common.remote.model.CuisineItem

interface GetCuisinesDataSource {
    suspend fun getAvailableCuisines(): List<CuisineItem>
}
