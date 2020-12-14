package me.ldrpontes.data.mappers

import java.lang.Exception

abstract class Mapper <Domain, Database, Networking> {

    open fun mapDatabaseToDomain(data: Database): Domain {
        throw Exception("Not implemented yet")
    }

    open fun mapNetworkingListToDatabaseList(data: List<Networking>): List<Database> {
        throw Exception("Not implemented yet")
    }

    open fun mapDatabaseListToDomainList(data: List<Database>): List<Domain> {
        throw Exception("Not implemented yet")
    }

    open fun  mapNetworkingToDatabase(data: Networking): Database {
        throw Exception("Not implemented yet")
    }

    open fun  mapNetworkingToDomain(data: Networking): Domain {
        throw Exception("Not implemented yet")
    }

}