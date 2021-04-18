package com.example.delishdemo2.common.remote.repository

import com.example.delishdemo2.common.remote.model.GetCuisinesDataSource
import com.example.delishdemo2.common.remote.model.CuisineItem
import javax.inject.Inject

class GetCuisinesRepository @Inject constructor(
    private val getCuisinesDataSource: GetCuisinesDataSource
) : CuisinesRepository {
    override suspend fun getAvailableCuisines(): List<CuisineItem> =
        getCuisinesDataSource.getAvailableCuisines()
}
