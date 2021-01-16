package me.ldrpontes.data.utils

import me.ldrpontes.domain.entities.Error
import java.lang.Exception

class HttpError(val code: Int, val error: Error, cause: Throwable?) : Exception(cause)