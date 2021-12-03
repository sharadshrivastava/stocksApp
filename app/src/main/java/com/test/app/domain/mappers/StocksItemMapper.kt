package com.test.app.domain.mappers

import com.test.app.data.common.parseToString
import com.test.app.domain.models.dto.StocksItemDto
import com.test.app.domain.models.ui.StocksItem
import java.math.BigDecimal
import java.time.Instant
import java.util.*

/**
 * This file is for converting the dto to UI objects.
 * Only this class knows how to convert to ui objects.
 */
fun toStockItem(stocksItemDto: StocksItemDto?) = StocksItem(
    currentPrice = toDollars(stocksItemDto?.currentPriceCents),
    ticker = stocksItemDto?.ticker,
    name = stocksItemDto?.name,
    currency = stocksItemDto?.currency,
    currentPriceTime = formatTime(stocksItemDto?.currentPriceTimestamp)
)

private  fun formatTime(timestamp: Int?) = timestamp?.let {
    Date.from(Instant.ofEpochSecond(it.toLong())).parseToString()
}

private fun toDollars(cents: Int?) = cents?.let {
    BigDecimal(cents).divide(BigDecimal.valueOf(100)).toString()
}

