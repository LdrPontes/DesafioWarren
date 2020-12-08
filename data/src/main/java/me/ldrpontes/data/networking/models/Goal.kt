package me.ldrpontes.data.networking.models

import com.google.gson.annotations.SerializedName

data class Goal(
    @SerializedName("_id")
    val id: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("totalBalance")
    var totalBalance: Double,
    @SerializedName("goalAmount")
    var goalAmount: Double?,
    @SerializedName("goalDate")
    var goalDate: String,
    @SerializedName("background")
    var background: Background
) {
    data class Background(
        @SerializedName("thumb")
        val thumb: String,
        @SerializedName("small")
        val small: String,
        @SerializedName("full")
        val full: String,
        @SerializedName("regular")
        val regular: String,
        @SerializedName("raw")
        val raw: String
    )
}