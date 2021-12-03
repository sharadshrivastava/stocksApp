package com.test.app.domain

import com.test.app.data.network.Resource
import com.test.app.domain.models.dto.ResponseDto

interface StocksRepository {

    suspend fun stocks(): Resource<ResponseDto?>
}