package com.app.moneybasetest.data.datascource.remote

import com.app.moneybasetest.data.model.GetAllSummaryResponseModel
import com.app.moneybasetest.data.model.GetItemSummaryResponseModel
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query



interface ApiService {

   // https://yh-finance.p.rapidapi.com/market/v2/get-summary?region=US
    //https://yh-finance.p.rapidapi.com/stock/v2/get-summary?region=US&symbol=^GSPC



    @GET("market/v2/get-summary?region=US")
    suspend fun getAllSummary(): GetAllSummaryResponseModel

    @GET("stock/v2/get-summary?region=US")
    suspend fun getSummaryItem(
        @Query("symbol") stockSymbol:String
    ): GetItemSummaryResponseModel

}