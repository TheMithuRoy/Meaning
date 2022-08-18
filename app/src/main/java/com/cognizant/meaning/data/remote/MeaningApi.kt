package com.cognizant.meaning.data.remote

import com.cognizant.meaning.data.remote.dto.MeaningDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MeaningApi {

    @GET("dictionary.py")
    suspend fun getMeanings(@Query("sf") sf: String): List<MeaningDto>
}