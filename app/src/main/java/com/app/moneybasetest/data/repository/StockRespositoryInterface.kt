package com.app.moneybasetest.data.repository

import com.app.moneybasetest.data.model.GetAllSummaryResponseModel
import com.app.moneybasetest.data.model.GetItemSummaryResponseModel
import com.app.moneybasetest.util.network.DataState
import kotlinx.coroutines.flow.Flow


interface StockRespositoryInterface {
    suspend fun getAllStock(): Flow<DataState<GetAllSummaryResponseModel>>
    suspend fun getStockDetail(stockSymbol: String): Flow<DataState<GetItemSummaryResponseModel>>
}