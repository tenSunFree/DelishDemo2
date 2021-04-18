package com.example.delishdemo2.common.remote.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.example.delishdemo2.common.util.DataStoreOperations
import com.example.delishdemo2.common.remote.model.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreRepository @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : DataStoreOperations {

    override suspend fun save(key: Preferences.Key<Boolean>, value: Boolean) {
        dataStore.edit {
            // Loggerg.d("DataStoreRepository, save, " +
            //         "key: $key, value: $value")
            it[key] = value
        }
    }

    override fun read(key: Preferences.Key<Boolean>): Flow<Result<Boolean>> {
        return dataStore.data
            .map {
                // Loggerg.d("DataStoreRepository, read, " +
                //         "key: ${key.name}, value: ${it[key] ?: false}")
                Result.Success(it[key] ?: false)
            }.catch {
                // Loggerg.d("DataStoreRepository, read, " +
                //         "Error, it: $it")
                Result.Error(Exception(it))
            }
    }
}
