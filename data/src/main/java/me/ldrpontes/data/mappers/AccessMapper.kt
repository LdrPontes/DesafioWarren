package me.ldrpontes.data.mappers

import me.ldrpontes.data.database.models.AccessDb
import me.ldrpontes.data.networking.models.AccessResponse
import me.ldrpontes.domain.entities.Access

class AccessMapper : Mapper<Access, AccessDb, AccessResponse>() {

    override fun mapDatabaseToDomain(data: AccessDb): Access = Access(
        data.accessToken,
        data.refreshToken
    )


    override fun mapNetworkingToDatabase(data: AccessResponse): AccessDb = AccessDb(
        accessToken = data.accessToken,
        refreshToken = data.refreshToken
    )

    override fun mapNetworkingToDomain(data: AccessResponse): Access = Access(
        data.accessToken,
        data.refreshToken
    )

}