package com.example.delishdemo2.common.remote.repository

import com.example.delishdemo2.common.remote.model.CuisineItem

interface CuisinesRepository {
    suspend fun getAvailableCuisines(): List<CuisineItem>
}
