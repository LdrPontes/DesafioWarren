package me.ldrpontes.domain.usecase

import me.ldrpontes.domain.entities.Access
import me.ldrpontes.domain.entities.DataResult
import me.ldrpontes.domain.repositories.AccessRepository

class GetAccessUseCase(private val repository: AccessRepository) : BaseUseCase<DataResult<Access>, Unit?> {
    override suspend fun execute(params: Unit?): DataResult<Access> {
        return repository.haveAccess()
    }
}