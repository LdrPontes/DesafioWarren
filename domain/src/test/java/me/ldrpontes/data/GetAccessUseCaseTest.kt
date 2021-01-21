package me.ldrpontes.data

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import me.ldrpontes.domain.entities.Access
import me.ldrpontes.domain.entities.DataResult
import me.ldrpontes.domain.repositories.AccessRepository
import me.ldrpontes.domain.usecase.GetAccessUseCase
import org.junit.Test

class GetAccessUseCaseTest {
    private val repository: AccessRepository = mock()
    private val useCase: GetAccessUseCase by lazy { GetAccessUseCase(repository) }

    @Test
    fun `GetAccessUseCase result success`() {
        runBlocking {
            whenever(repository.haveAccess()).thenReturn(DataResult.Success(mock()))

            val result = useCase.execute()
            verify(repository).haveAccess()

            assert(result is DataResult.Success)
        }
    }

    @Test
    fun `GetAccessUseCase result failure`() {
        runBlocking {
            whenever(repository.haveAccess()).thenReturn(DataResult.Failure(mock()))

            val result = useCase.execute()
            verify(repository).haveAccess()

            assert(result is DataResult.Failure)
        }
    }

}