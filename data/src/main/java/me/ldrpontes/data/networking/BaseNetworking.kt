package me.ldrpontes.data.networking

import me.ldrpontes.data.utils.HttpError
import retrofit2.Response
import java.lang.Exception

inline fun <T : Any> Response<T>.getResult(
    onSuccess: (T) -> Unit,
    onFailure: (e: Exception) -> Unit
) {
    try {
        val body = body()

        if (isSuccessful && body != null) {
            onSuccess(body)
        } else {
            onFailure(HttpError(code(), Throwable(message())))
        }
    } catch (e: Exception) {
        onFailure(e)
    }

}