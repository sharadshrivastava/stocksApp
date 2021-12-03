package com.test.app.domain.models.ui

/**
 * Created separate UI data class for stock item because
 * 1.All fields of DTOs are not required in UI data class.
 * 2.In future this class can have values from some other DTOs as well.
 */
data class StocksItem(

    val currentPrice: String? = null,

    val ticker: String? = null,

    val name: String? = null,

    val currency: String? = null,

    val currentPriceTime: String? = null
)
