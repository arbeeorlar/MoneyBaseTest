package com.app.moneybasetest.data.repository

import com.app.moneybasetest.data.model.GetAllSummaryResponseModel
import com.app.moneybasetest.data.model.GetItemSummaryResponseModel
import com.app.moneybasetest.util.network.DataState
import kotlinx.coroutines.flow.Flow


interface StockRepositoryInterface {
    suspend fun getAllStocks(): Flow<DataState<GetAllSummaryResponseModel>>
    suspend fun getStockDetail(stockSymbol: String): Flow<DataState<GetItemSummaryResponseModel>>
}