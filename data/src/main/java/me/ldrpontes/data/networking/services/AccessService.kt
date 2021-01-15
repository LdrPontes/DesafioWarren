package me.ldrpontes.data.networking.services

import me.ldrpontes.data.networking.models.AccessResponse
import me.ldrpontes.data.networking.models.AccessBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AccessService {

    @POST("account/login")
    suspend fun doLogin(@Body body: AccessBody): Response<AccessResponse>

}