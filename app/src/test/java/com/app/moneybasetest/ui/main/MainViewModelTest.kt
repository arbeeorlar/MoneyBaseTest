package com.app.moneybasetest.ui.main


import android.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.app.moneybasetest.data.model.GetAllSummaryResponseModel
import com.app.moneybasetest.data.model.MarketSummaryAndSparkResponse
import com.app.moneybasetest.data.repository.StockRepository
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


@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()

    // Mock the repository and observer
    @Mock
    private lateinit var repo: StockRepository

    @Mock
    private lateinit var stockItemsObserver: Observer<DataState<GetAllSummaryResponseModel>>

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testGetAllSummary() = testDispatcher.runBlockingTest {
        val viewModel = MainViewModel(repo)
        viewModel.stockItems.observeForever(stockItemsObserver)

        // Create a fake response
        val fakeResponse = DataState.Success(GetAllSummaryResponseModel(
            marketSummaryAndSparkResponse = MarketSummaryAndSparkResponse(null, emptyList())
        ))
        Mockito.`when`(repo.getAllStocks()).thenReturn(flowOf(fakeResponse))
        viewModel.getAllSummary()
        delay(8000) // Adjust the delay time as needed
        Mockito.verify(stockItemsObserver).onChanged(fakeResponse)
    }
}
