package me.ldrpontes.data

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import me.ldrpontes.domain.entities.DataResult
import me.ldrpontes.domain.repositories.AccessRepository
import me.ldrpontes.domain.usecase.LoginParams
import me.ldrpontes.domain.usecase.LoginUseCase
import org.junit.Test
import java.lang.Exception

class LoginUseCaseTest {
    private val repository:  AccessRepository = mock()
    private val useCase: LoginUseCase by lazy { LoginUseCase(repository) }

    @Test
    fun `LoginUseCase result success`(){
        runBlocking {
            val result = useCase.execute(LoginParams("leandropberleze@gmail.com", "12345678"))
            verify(repository).doLogin("leandropberleze@gmail.com", "12345678")

            assert(result is DataResult.Success)
        }
    }

    @Test
    fun `LoginUseCase result failure`(){
        runBlocking {
            whenever(repository.doLogin("leandropberleze@gmail.com", "12345678")).thenReturn(DataResult.Failure(Exception()))

            val result = useCase.execute(LoginParams("leandropberleze@gmail.com", "12345678"))
            verify(repository).doLogin("leandropberleze@gmail.com", "12345678")

            assert(result is DataResult.Failure)
        }
    }

}