package com.app.moneybasetest.ui.main


import android.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.app.moneybasetest.data.model.GetAllSummaryResponseModel
import com.app.moneybasetest.data.model.MarketSummaryAndSparkResponse
import com.app.moneybasetest.data.repository.StockRepository
import com.app.moneybasetest.ui.screens.mainScreen.Counter
import com.app.moneybasetest.ui.screens.mainScreen.MainViewModel
import com.app.moneybasetest.util.network.DataState
import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import java.lang.Exception
import java.util.Timer
import java.util.TimerTask


@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()
    private lateinit var testScope: TestCoroutineScope

    @Mock
    private lateinit var repo: StockRepository

    @Mock
    private lateinit var stockItemsObserver: Observer<DataState<GetAllSummaryResponseModel>>

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        testScope = TestCoroutineScope(testDispatcher)
    }

    @Test
    fun testGetAllSummarySuccess() = testScope.runBlockingTest {
        //Dispatchers.setMain(this)
        val viewModel = MainViewModel(repo)
        viewModel.stockItems.observeForever(stockItemsObserver)
        val fakeResponse = DataState.Success(GetAllSummaryResponseModel(
            marketSummaryAndSparkResponse = MarketSummaryAndSparkResponse(null, emptyList())
        ))
        Mockito.`when`(repo.getAllStocks()).thenReturn(flowOf(fakeResponse))
        viewModel.getAllSummary()
        Mockito.verify(stockItemsObserver).onChanged(fakeResponse)
    }

    @Test
    fun testGetAllSummaryFailure() = testScope.runBlockingTest {
        val viewModel = MainViewModel(repo)
        viewModel.stockItems.observeForever(stockItemsObserver)
        val fakeError = DataState.Error(Exception("Failed to fetch all summary"))
        Mockito.`when`(repo.getAllStocks()).thenReturn(flowOf(fakeError))
        viewModel.getAllSummary()
        Mockito.verify(stockItemsObserver).onChanged(fakeError)
    }
}



