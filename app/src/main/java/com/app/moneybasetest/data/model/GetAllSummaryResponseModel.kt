package com.app.moneybasetest.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GetAllSummaryResponseModel(
    val marketSummaryAndSparkResponse: MarketSummaryAndSparkResponse
)

data class MarketSummaryAndSparkResponse(
    val error: Any? = null,
    @SerializedName("result")
    val result: List<StockItem>
)

data class StockItem(
    val cryptoTradeable: Boolean,
    val customPriceAlertConfidence: String,
    val exchange: String,
    val exchangeDataDelayedBy: Int,
    val exchangeTimezoneName: String,
    val exchangeTimezoneShortName: String,
    val firstTradeDateMilliseconds: Long,
    val fullExchangeName: String,
    val gmtOffSetMilliseconds: Int,
    val language: String,
    val market: String,
    val marketState: String,
    val priceHint: Int,
    val quoteType: String,
    val region: String,
   val regularMarketPreviousClose: RegularMarketPreviousClose,
    val regularMarketTime: RegularMarketTime,
    val shortName: String,
    val sourceInterval: Int,
    val spark: Spark,
    val symbol: String,
    val tradeable: Boolean,
    val triggerable: Boolean
): Serializable

data class RegularMarketTime(
    val fmt: String,
    val raw: Int
): Serializable

data class RegularMarketPreviousClose(
    val fmt: String,
    val raw: Double
): Serializable

data class Spark(
    val chartPreviousClose: Double,
    val close: List<Double>,
    val dataGranularity: Int,
    val end: Int,
    val previousClose: Double,
    val start: Int,
    val symbol: String,
    val timestamp: List<Int>
): Serializable