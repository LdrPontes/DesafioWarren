package me.ldrpontes.data.networking.models

import com.google.gson.annotations.SerializedName
import me.ldrpontes.data.database.models.AccessDb

data class AccessResponse(

    @SerializedName("accessToken")
    val accessToken: String,
    @SerializedName("refreshToken")
    val refreshToken: String,

    )