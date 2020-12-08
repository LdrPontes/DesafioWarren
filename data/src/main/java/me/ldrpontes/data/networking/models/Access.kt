package me.ldrpontes.data.networking.models

import com.google.gson.annotations.SerializedName

data class Access(
    @SerializedName("accessToken")
    val accessToken: String,
    @SerializedName("refreshToken")
    val refreshToken: String,
)