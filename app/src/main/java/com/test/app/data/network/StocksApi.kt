package com.test.app.data.network

import com.test.app.domain.models.dto.ResponseDto
import retrofit2.http.GET

/**
 * Retrofit library's network interface.
 */
interface StocksApi {

    @GET(STOCKS_PATH)
    suspend fun stocks(): ResponseDto?

    companion object {
        const val BASE_URL = "https://storage.googleapis.com/"
        private const val STOCKS_PATH = "cash-homework/cash-stocks-api/portfolio.json"
    }
}