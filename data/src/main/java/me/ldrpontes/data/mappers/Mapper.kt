package me.ldrpontes.data.mappers

interface Mapper <Domain, Database, Networking> {

    fun mapDatabaseToDomain(data: Database): Domain

    fun mapNetworkingListToDatabaseList(data: List<Networking>): List<Database>

    fun mapDatabaseListToDomainList(data: List<Database>): List<Domain>

    fun  mapNetworkingToDatabase(data: Networking): Database

}