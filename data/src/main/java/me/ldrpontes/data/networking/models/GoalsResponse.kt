package me.ldrpontes.data.networking.models

import com.google.gson.annotations.SerializedName

data class GoalsResponse(
    @SerializedName("portfolios")
    val portfolios: List<Goal>
)