package com.example.delishdemo2.common.remote.model

import com.example.delishdemo2.common.remote.DD2Api
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetCuisinesRemoteDataSource @Inject constructor(
    private val api: DD2Api,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : GetCuisinesDataSource {
    override suspend fun getAvailableCuisines(): List<CuisineItem> =
        withContext(ioDispatcher) {
            api.getAvailableCuisines()
        }
}
