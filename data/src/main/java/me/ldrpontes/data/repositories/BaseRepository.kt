package me.ldrpontes.data.repositories

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import java.lang.Exception

abstract class BaseRepository {

    suspend fun <T> fetchData(api: suspend () -> T, local: suspend (T) -> Flow<T>): Flow<T> {
        return flow {

        }
    }
}