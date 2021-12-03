package com.test.app.domain.models.dto

import com.google.gson.annotations.SerializedName

data class StocksItemDto(

    @field:SerializedName("current_price_cents")
    val currentPriceCents: Int? = null,

    @field:SerializedName("ticker")
    val ticker: String? = null,

    @field:SerializedName("quantity")
    val quantity: Any? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("currency")
    val currency: String? = null,

    @field:SerializedName("current_price_timestamp")
    val currentPriceTimestamp: Int? = null
) {

    //as per documentation, below values should not be null in valid response.
    fun isValid() =
        currentPriceCents != null && ticker != null && name != null
                && currency != null && currentPriceTimestamp != null
}