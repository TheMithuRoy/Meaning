@file:OptIn(ExperimentalCoroutinesApi::class)

package com.cognizant.meaning.domain.usecase

import android.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.cognizant.common.Resource
import com.cognizant.meaning.data.remote.dto.LongFormDto
import com.cognizant.meaning.data.remote.dto.MeaningDto
import com.cognizant.meaning.domain.repository.MeaningRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`

class GetMeaningsUseCaseTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val repo: MeaningRepository = Mockito.mock(MeaningRepository::class.java)

    val useCase by lazy { GetMeaningsUseCase(repo) }

    @Test
    fun `flows resets to loading state at the beginning`() = runTest {
        `when`(repo.getMeanings("cts")).thenReturn(emptyList())

        useCase("cts").take(1).test {
            val state = awaitItem()
            assert(state is Resource.Loading)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `flow goes to success state once it gets the data`() = runTest {
        val data = listOf(MeaningDto("", listOf(LongFormDto("Cognizant", 50, 1990))))
        `when`(repo.getMeanings("cts")).thenReturn(data)

        useCase("cts").drop(1).test {
            val state = awaitItem()
            assert(state is Resource.Success)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `flow fetches the data list correctly`() = runTest {
        val data = listOf(MeaningDto("", listOf(LongFormDto("Cognizant", 50, 1990))))
        `when`(repo.getMeanings("cts")).thenReturn(data)

        useCase("cts").drop(1).test {
            val state = awaitItem()
            assert(state.data?.first()?.longForm == "Cognizant")
            cancelAndIgnoreRemainingEvents()
        }
    }
}