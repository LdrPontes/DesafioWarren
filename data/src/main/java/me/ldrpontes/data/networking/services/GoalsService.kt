package me.ldrpontes.data.networking.services

import me.ldrpontes.data.networking.models.ListGoalsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface GoalsService {

    @GET("portfolios/mine")
    suspend fun getAll(@Header("access-token") token: String): Response<ListGoalsResponse>

}