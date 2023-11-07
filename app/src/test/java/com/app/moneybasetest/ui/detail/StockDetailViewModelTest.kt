package com.app.moneybasetest.ui.detail

import android.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.app.moneybasetest.data.model.GetAllSummaryResponseModel
import com.app.moneybasetest.data.model.GetItemSummaryResponseModel
import com.app.moneybasetest.data.model.MarketSummaryAndSparkResponse
import com.app.moneybasetest.data.model.Price
import com.app.moneybasetest.data.repository.StockRepository
import com.app.moneybasetest.ui.screens.detail.StockDetailViewModel
import com.app.moneybasetest.ui.screens.mainScreen.MainViewModel
import com.app.moneybasetest.util.network.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import kotlinx.coroutines.test.runBlockingTest
import java.lang.Exception


@ExperimentalCoroutinesApi
class StockDetailViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()

    // Mock the repository and observer
    @Mock
    private lateinit var repo: StockRepository

    @Mock
    private lateinit var stockObjectItemsObserver: Observer<DataState<GetItemSummaryResponseModel>>

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testGetStockItemSuccess() = testDispatcher.runBlockingTest {
        val viewModel = StockDetailViewModel(repo)
        viewModel.stockObjectItems.observeForever(stockObjectItemsObserver)
        val fakeResponse = DataState.Success(GetItemSummaryResponseModel(
            price = null,
            quoteType = null,
            summaryDetail = null,
            ""
            ))
        Mockito.`when`(repo.getStockDetail(Mockito.anyString())).thenReturn(flowOf(fakeResponse))
        viewModel.getStockItem("AAPL")
        Mockito.verify(stockObjectItemsObserver).onChanged(fakeResponse)
    }

    @Test
    fun testGetStockItemFailure() = testDispatcher.runBlockingTest {
        val viewModel = StockDetailViewModel(repo)
        viewModel.stockObjectItems.observeForever(stockObjectItemsObserver)
        val fakeError = DataState.Error(Exception(message = "Failed to fetch stock details" ))
        Mockito.`when`(repo.getStockDetail(Mockito.anyString())).thenReturn(flowOf(fakeError))
        viewModel.getStockItem("InvalidSymbol") // Use an invalid stock symbol
        Mockito.verify(stockObjectItemsObserver).onChanged(fakeError)
    }
}
