package me.ldrpontes.domain.usecase

import me.ldrpontes.domain.entities.Access
import me.ldrpontes.domain.entities.DataResult
import me.ldrpontes.domain.repositories.AccessRepository

class GetAccessUseCase(private val repository: AccessRepository) :
    BaseUseCaseNoParams<DataResult<Access>> {
    override suspend fun execute(): DataResult<Access> {
        return repository.haveAccess()
    }
}