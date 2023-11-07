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
import java.util.Timer
import java.util.TimerTask


@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()

    @Mock
    private lateinit var repo: StockRepository

    @Mock
    private lateinit var stockItemsObserver: Observer<DataState<GetAllSummaryResponseModel>>

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testGetAllSummarySuccess() = testDispatcher.runBlockingTest {
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
    fun testGetAllSummaryFailure() = testDispatcher.runBlockingTest {
        val viewModel = MainViewModel(repo)
        viewModel.stockItems.observeForever(stockItemsObserver)
        val fakeError = DataState.Error(Exception("Failed to fetch all summary"))
        Mockito.`when`(repo.getAllStocks()).thenReturn(flowOf(fakeError))
        viewModel.getAllSummary()
        Mockito.verify(stockItemsObserver).onChanged(fakeError)
    }
}

class CounterTest {

    @Test
    fun testCounterStart() {
        val viewModel = Mockito.mock(MainViewModel::class.java)
        val counter = Counter(viewModel)
        val timer = Mockito.mock(Timer::class.java)
        val timerTask = Mockito.mock(TimerTask::class.java)
        Mockito.`when`(timer.schedule(Mockito.any(), Mockito.anyLong(), Mockito.anyLong())).thenAnswer {
            val task = it.arguments[0] as TimerTask
            task.run()
        }
        counter.start()
        Mockito.verify(viewModel).getAllSummary()
    }
}

