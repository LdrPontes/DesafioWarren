package me.ldrpontes.warrenbrasil.utils

sealed class State<out T> {
    data class Success<out T>(val data: T) : State<T>()
    data class Failure(val message: String) : State<Nothing>()
    data class Loading(val loading: Boolean) : State<Nothing>()
}

inline fun <T : Any> State<T>.onSuccess(action: (T) -> Unit): State<T> {
    if (this is State.Success) action(data)
    return this
}

inline fun <T : Any> State<T>.onFailure(action: (String) -> Unit) {
    if (this is State.Failure) action(message)
}

inline fun <T : Any> State<T>.onLoading(action: (Boolean) -> Unit) {
    if (this is State.Loading) action(loading)
}