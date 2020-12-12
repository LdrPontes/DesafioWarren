package me.ldrpontes.data.networking.models

import com.google.gson.annotations.SerializedName
import me.ldrpontes.domain.entities.Background
import me.ldrpontes.domain.entities.Goal

data class GoalResponse(

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
    var background: BackgroundResponse

) : DatabaseMapper<Goal> {

    data class BackgroundResponse(
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

    override fun mapToDatabase(): Goal = Goal(
        id,
        name,
        totalBalance,
        goalAmount,
        goalDate,
        Background(
            background.thumb,
            background.small,
            background.full,
            background.regular,
            background.raw
        )
    )

}