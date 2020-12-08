package me.ldrpontes.data.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "access")
data class Access(
    @PrimaryKey val id: Int = 1,
    @ColumnInfo(name = "access_token") val accessToken: String,
    @ColumnInfo(name = "refresh_token") val refreshToken: String
)