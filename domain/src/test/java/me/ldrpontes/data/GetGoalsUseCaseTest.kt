package me.ldrpontes.data

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import me.ldrpontes.domain.entities.DataResult
import me.ldrpontes.domain.entities.Goal
import me.ldrpontes.domain.repositories.GoalsRepository
import me.ldrpontes.domain.usecase.GetGoalsParams
import me.ldrpontes.domain.usecase.GetGoalsUseCase
import org.junit.Test

class GetGoalsUseCaseTest {
    private val repository: GoalsRepository = mock()
    private val useCase: GetGoalsUseCase by lazy { GetGoalsUseCase(repository) }

    @Test
    fun `GetGoalsUseCase result success`() {
        runBlocking {
            whenever(repository.getGoals("token")).thenReturn(
                flow {
                    emit(DataResult.Success<List<Goal>>(mock()))
                }
            )

            val result = useCase.execute(GetGoalsParams("token")).first()

            verify(repository).getGoals("token")

            assert(result is DataResult.Success)
        }
    }

    @Test
    fun `GetGoalsUseCase result failure`() {
        runBlocking {
            whenever(repository.getGoals("token")).thenReturn(
                flow {
                    emit(DataResult.Failure(mock()))
                }
            )

            val result = useCase.execute(GetGoalsParams("token")).first()
            verify(repository).getGoals("token")

            assert(result is DataResult.Failure)
        }
    }

    @Test
    fun `GetGoalsUseCase return all results from flow`() {
        runBlocking {
            whenever(repository.getGoals("token")).thenReturn(
                flow {
                    emit(DataResult.Success<List<Goal>>(mock()))
                    delay(2000)
                    emit(DataResult.Failure(mock()))
                }
            )

            val result = useCase.execute(GetGoalsParams("token")).take(2).toList()
            verify(repository).getGoals("token")

            assert(result[0] is DataResult.Success)
            assert(result[1] is DataResult.Failure)
        }
    }

}