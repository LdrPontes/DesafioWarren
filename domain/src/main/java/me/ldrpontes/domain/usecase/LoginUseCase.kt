package me.ldrpontes.domain.usecase

import me.ldrpontes.domain.entities.Access
import me.ldrpontes.domain.entities.Result
import me.ldrpontes.domain.repositories.AccessRepository

class LoginUseCase(private val repository: AccessRepository) :
    BaseUseCase<Result<Access>, LoginParams> {

    override suspend fun execute(params: LoginParams): Result<Access> {
        
        return repository.doLogin(email = params.email, password = params.password)
    }

}

data class LoginParams(val email: String, val password: String)