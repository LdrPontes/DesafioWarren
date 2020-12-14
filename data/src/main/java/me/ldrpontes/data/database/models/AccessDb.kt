package me.ldrpontes.data.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import me.ldrpontes.domain.entities.Access

@Entity(tableName = "access")
data class AccessDb(

    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "access_token") val accessToken: String,
    @ColumnInfo(name = "refresh_token") val refreshToken: String

)