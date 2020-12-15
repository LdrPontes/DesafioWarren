package me.ldrpontes.domain.entities

import java.lang.Exception

sealed class DataResult <out T> {
    data class Success<out T>(val data: T) : DataResult<T>()
    data class Failure(val exception: Exception) : DataResult<Nothing>()
}

inline fun <T : Any> DataResult<T>.onSuccess(action: (T) -> Unit): DataResult<T> {
    if (this is DataResult.Success) action(data)
    return this
}

inline fun <T : Any> DataResult<T>.onFailure(action: (Exception) -> Unit) {
    if (this is DataResult.Failure) action(exception)
}
