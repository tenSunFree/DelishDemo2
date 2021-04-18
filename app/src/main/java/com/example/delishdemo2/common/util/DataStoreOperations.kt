package com.example.delishdemo2.common.util

import androidx.datastore.preferences.core.Preferences
import com.example.delishdemo2.common.remote.model.Result
import kotlinx.coroutines.flow.Flow

interface DataStoreOperations {
    suspend fun save(key: Preferences.Key<Boolean>, value: Boolean)
    fun read(key: Preferences.Key<Boolean>): Flow<Result<Boolean>>
}
