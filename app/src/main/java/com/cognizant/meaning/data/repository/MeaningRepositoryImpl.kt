package com.cognizant.meaning.data.repository

import com.cognizant.meaning.data.remote.MeaningApi
import com.cognizant.meaning.domain.repository.MeaningRepository
import javax.inject.Inject

class MeaningRepositoryImpl @Inject constructor(
    private val api: MeaningApi
) : MeaningRepository {

    override suspend fun getMeanings(sf: String) = api.getMeanings(sf)
}