package me.ldrpontes.domain.entities

import java.lang.Exception

sealed class Result <out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Failure(val exception: Exception) : Result<Nothing>()
}

inline fun <T : Any> Result<T>.onSuccess(action: (T) -> Unit): Result<T> {
    if (this is Result.Success) action(data)
    return this
}

inline fun <T : Any> Result<T>.onFailure(action: (Exception) -> Unit) {
    if (this is Result.Failure) action(exception)
}
