package com.app.moneybasetest.data.model

data class GetItemSummaryResponseModel(
    val price: Price? = null,
    val quoteType: QuoteType? = null,
    val summaryDetail: SummaryDetail? = null,
    val symbol: String
)

data class Ask(
    val fmt: String,
    val raw: Double
)

data class AskSize(
    val fmt: Any,
    val longFmt: String,
    val raw: Int
)

data class AverageDailyVolume3Month(
    val fmt: String,
    val longFmt: String,
    val raw: Long
)

data class AverageDailyVolume10Day(
    val fmt: String,
    val longFmt: String,
    val raw: Long
)

data class AverageVolume(
    val fmt: String,
    val longFmt: String,
    val raw: Long
)

data class AverageVolume10days(
    val fmt: String,
    val longFmt: String,
    val raw: Long
)

data class Bid(
    val fmt: String,
    val raw: Double
)

data class DayHigh(
    val fmt: String,
    val raw: Double
)

data class DayLow(
    val fmt: String,
    val raw: Double
)

data class FiftyDayAverage(
    val fmt: String,
    val raw: Double
)

data class FiftyTwoWeekHigh(
    val fmt: String,
    val raw: Double
)

data class FiftyTwoWeekLow(
    val fmt: String,
    val raw: Double
)

data class Open(
    val fmt: String,
    val raw: Double
)


data class PreviousClose(
    val fmt: String,
    val raw: Double
)

data class Price(
    val averageDailyVolume10Day: AverageDailyVolume10Day,
    val averageDailyVolume3Month: AverageDailyVolume3Month,
    val currency: String,
    val currencySymbol: String,
    val exchange: String,
    val exchangeDataDelayedBy: Int,
    val exchangeName: String,
    val fromCurrency: Any,
    val lastMarket: Any,
    val longName: String,
    val marketState: String,
    val maxAge: Int,
    val priceHint: PriceHint,
    val quoteSourceName: String,
    val quoteType: String,
    val regularMarketChangePercent: RegularMarketChangePercent,
    val regularMarketDayHigh: RegularMarketDayHigh,
    val regularMarketDayLow: RegularMarketDayLow,
    val regularMarketOpen: RegularMarketOpen,
    val regularMarketPreviousClose: RegularMarketPreviousClose,
    val regularMarketPrice: RegularMarketPrice,
    val regularMarketSource: String,
    val regularMarketTime: Int,
    val regularMarketVolume: RegularMarketVolume,
    val shortName: String,
    val symbol: String,
    val toCurrency: Any,
    val underlyingSymbol: Any,
)

data class PriceHint(
    val fmt: String,
    val longFmt: String,
    val raw: Int
)

data class QuoteType(
    val exchange: String,
    val exchangeTimezoneName: String,
    val exchangeTimezoneShortName: String,
    val gmtOffSetMilliseconds: String,
    val isEsgPopulated: Boolean,
    val longName: String,
    val market: String,
    val messageBoardId: String,
    val quoteType: String,
    val shortName: String,
    val symbol: String
)

data class RegularMarketChangePercent(
    val fmt: String,
    val raw: Double
)

data class RegularMarketDayHigh(
    val fmt: String,
    val raw: Double
)

data class RegularMarketDayLow(
    val fmt: String,
    val raw: Double
)

data class RegularMarketOpen(
    val fmt: String,
    val raw: Double
)

data class RegularMarketPrice(
    val fmt: String,
    val raw: Double
)

data class RegularMarketVolume(
    val fmt: String,
    val longFmt: String,
    val raw: Long
)

data class SummaryDetail(
    val algorithm: Any,
    val ask: Ask,
    val askSize: AskSize,
    val averageDailyVolume10Day: AverageDailyVolume10Day,
    val averageVolume: AverageVolume,
    val averageVolume10days: AverageVolume10days,
    val bid: Bid,
    val coinMarketCapLink: Any,
    val currency: String,
    val dayHigh: DayHigh,
    val dayLow: DayLow,
    val fiftyDayAverage: FiftyDayAverage,
    val fiftyTwoWeekHigh: FiftyTwoWeekHigh,
    val fiftyTwoWeekLow: FiftyTwoWeekLow,
    val fromCurrency: Any,
    val lastMarket: Any,
    val maxAge: Int,
    val open : Open,
    val previousClose: PreviousClose,

    val toCurrency: Any,
    val tradeable: Boolean,
    val twoHundredDayAverage: TwoHundredDayAverage,
    val volume: Volume,

)

data class TwoHundredDayAverage(
    val fmt: String,
    val raw: Double
)

data class Volume(
    val fmt: String,
    val longFmt: String,
    val raw: Long
)