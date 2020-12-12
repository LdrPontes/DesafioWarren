package me.ldrpontes.data.utils

import java.lang.Exception

class HttpError(code: Int, cause: Throwable?) : Exception(cause)