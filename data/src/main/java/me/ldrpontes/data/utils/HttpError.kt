package me.ldrpontes.data.utils

import okhttp3.ResponseBody
import java.lang.Exception

class HttpError(code: Int, errorBody: ResponseBody?, cause: Throwable?) : Exception(cause)