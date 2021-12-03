package com.test.app.data

import com.test.app.data.network.StocksApi
import com.test.app.data.network.Resource
import com.test.app.data.network.ResponseHandler
import com.test.app.domain.StocksRepository
import com.test.app.domain.models.dto.ResponseDto
import javax.inject.Inject
import javax.inject.Singleton

/**
 * This is a concrete implementation of repository.
 */
@Singleton
class StocksRepositoryImpl @Inject constructor(private val api: StocksApi) : StocksRepository {

    override suspend fun stocks(): Resource<ResponseDto?> = try {
        ResponseHandler.handleSuccess(api.stocks())
    } catch (e: Exception) {
        ResponseHandler.handleException(e)
    }
}