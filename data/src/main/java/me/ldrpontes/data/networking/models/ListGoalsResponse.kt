package me.ldrpontes.data.networking.models

import com.google.gson.annotations.SerializedName

data class ListGoalsResponse(
    @SerializedName("portfolios")
    val portfolios: List<GoalResponse>
)