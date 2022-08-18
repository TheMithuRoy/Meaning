package com.cognizant.meaning.domain.usecase

import com.cognizant.common.Resource
import com.cognizant.meaning.data.remote.dto.toMeaning
import com.cognizant.meaning.domain.model.Meaning
import com.cognizant.meaning.domain.repository.MeaningRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMeaningsUseCase @Inject constructor(
    private val repository: MeaningRepository
) {

    operator fun invoke(sf: String) = flow<Resource<List<Meaning>>> {
        try {
            emit(Resource.Loading())
            val meanings = mutableListOf<Meaning>()
            repository.getMeanings(sf).forEach {
                val mn = it.lfs.map { lf -> lf.toMeaning() }
                meanings.addAll(mn)
            }
            emit(Resource.Success(meanings))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Something Went Wrong"))
        } catch (e: IOException) {
            emit(Resource.Error("Internet is Unavailable"))
        }
    }
}