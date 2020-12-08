package me.ldrpontes.data.networking.models

import com.google.gson.annotations.SerializedName

data class AccessBody(
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String
)