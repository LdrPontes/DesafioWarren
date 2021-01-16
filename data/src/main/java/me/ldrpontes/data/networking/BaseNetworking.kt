package me.ldrpontes.data.networking

import com.google.gson.Gson
import me.ldrpontes.data.utils.HttpError
import me.ldrpontes.domain.entities.Error
import retrofit2.Response
import java.lang.Exception


fun <T : Any> Response<T>.isResponseSuccessful(): Boolean {

    val body = body()

    return isSuccessful && body != null
}

fun <T : Any> Response<T>.failure(): Exception? {
    return if (isResponseSuccessful()) {
        null
    } else {
        HttpError(
            code(),
            if (errorBody() != null) Gson().fromJson(
                errorBody()!!.string(),
                Error::class.java
            ) else Error(""),
            Throwable(message())
        )
    }

}


inline fun <T : Any> Response<T>.handle(
    onSuccess: (T) -> Unit,
    onFailure: (e: Exception) -> Unit
) {
    try {

        if (isResponseSuccessful()) {
            onSuccess(body()!!)
        } else {
            onFailure(
                HttpError(
                    code(),
                    if (errorBody() != null) Gson().fromJson(
                        errorBody()!!.string(),
                        Error::class.java
                    ) else Error(""),
                    Throwable(message())
                )
            )
        }
    } catch (e: Exception) {
        onFailure(e)
    }

}
