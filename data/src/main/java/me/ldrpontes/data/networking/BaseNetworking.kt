package me.ldrpontes.data.networking

import me.ldrpontes.data.utils.HttpError
import retrofit2.Response
import java.lang.Exception


fun <T : Any> Response<T>.isResponseSuccessful(): Boolean {

    val body = body()

    return isSuccessful && body != null
}


inline fun <T : Any> Response<T>.handle(
    onSuccess: (T) -> Unit,
    onFailure: (e: Exception) -> Unit
) {
    try {
        val body = body()

        if (isSuccessful && body != null) {
            onSuccess(body)
        } else {
            onFailure(HttpError(code(), errorBody(), Throwable(message())))
        }
    } catch (e: Exception) {
        onFailure(e)
    }

}
