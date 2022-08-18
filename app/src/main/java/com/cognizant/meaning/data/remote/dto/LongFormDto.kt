package com.cognizant.meaning.data.remote.dto

import com.cognizant.meaning.domain.model.Meaning

data class LongFormDto(
    val lf: String,
    val freq: Int,
    val since: Int
)

fun LongFormDto.toMeaning() = Meaning(
    longForm = lf,
    since = since.toString()
)