@file:OptIn(ExperimentalCoroutinesApi::class)

package com.cognizant.meaning.data.repository

import com.cognizant.meaning.data.remote.MeaningApi
import com.cognizant.meaning.data.remote.dto.LongFormDto
import com.cognizant.meaning.data.remote.dto.MeaningDto
import com.cognizant.meaning.domain.repository.MeaningRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`

class MeaningRepositoryImplTest {

    private val api: MeaningApi = Mockito.mock(MeaningApi::class.java)

    private val repository: MeaningRepository by lazy { MeaningRepositoryImpl(api) }

    @Test
    fun `returns empty list if response is empty array`() = runTest {
        `when`(api.getMeanings("")).thenReturn(emptyList())

        val response = repository.getMeanings("")
        assert(response.isEmpty())
    }

    @Test
    fun `returns correct data from api`() = runTest {
        val data = listOf(MeaningDto("", listOf(LongFormDto("Cognizant", 50, 1990))))
        `when`(api.getMeanings("CTS")).thenReturn(data)

        val firstLf = repository.getMeanings("CTS").first().lfs.first().lf
        assert(firstLf == "Cognizant")
    }

}