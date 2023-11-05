package com.app.moneybasetest.data.repository

import com.app.moneybasetest.data.datascource.remote.ApiService
import com.app.moneybasetest.data.model.GetAllSummaryResponseModel
import com.app.moneybasetest.data.model.GetItemSummaryResponseModel
import com.app.moneybasetest.util.network.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject



class StockRespository @Inject constructor(
    private val apiService: ApiService
) : StockRespositoryInterface {

    override suspend fun getAllStock(): Flow<DataState<GetAllSummaryResponseModel>> = flow {
        emit(DataState.Loading)
        try {
            val allStockItemsResult = apiService.getAllSummary()
            emit(DataState.Success(allStockItemsResult))

        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    override suspend fun getStockDetail(stockSymbol: String): Flow<DataState<GetItemSummaryResponseModel>> = flow {
        emit(DataState.Loading)
        try {
            val stockItemResult = apiService.getSummaryItem(stockSymbol)
            emit(DataState.Success(stockItemResult))

        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}