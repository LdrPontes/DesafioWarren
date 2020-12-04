package me.ldrpontes.domain.usecase

import me.ldrpontes.domain.entities.Access
import me.ldrpontes.domain.usecase.BaseUseCase
import me.ldrpontes.domain.repositories.AuthRepository

class LoginUseCase(private val repository: AuthRepository) :
    BaseUseCase<LoginResponse, LoginParams> {

    override suspend fun execute(params: LoginParams): LoginResponse {

        val result = repository.doLogin(email = params.email, password = params.password)

        return LoginResponse(result)
    }

}

data class LoginParams(val email: String, val password: String)

data class LoginResponse(val access: Access)