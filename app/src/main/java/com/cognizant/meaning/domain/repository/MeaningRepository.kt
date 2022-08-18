package com.cognizant.meaning.domain.repository

import com.cognizant.meaning.data.remote.dto.MeaningDto

interface MeaningRepository {

    suspend fun getMeanings(sf: String): List<MeaningDto>
}