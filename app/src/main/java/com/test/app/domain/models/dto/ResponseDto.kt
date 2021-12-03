package com.test.app.domain.models.dto

import com.google.gson.annotations.SerializedName

data class ResponseDto(

    @field:SerializedName("stocks")
    val stocks: List<StocksItemDto?>? = null
)
