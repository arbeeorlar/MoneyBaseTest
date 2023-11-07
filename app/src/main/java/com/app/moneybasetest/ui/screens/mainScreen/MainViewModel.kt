package com.app.moneybasetest.ui.screens.mainScreen

import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.moneybasetest.data.model.GetAllSummaryResponseModel
import com.app.moneybasetest.data.model.StockItem
import com.app.moneybasetest.data.repository.StockRepository
import com.app.moneybasetest.util.network.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.util.Timer
import java.util.TimerTask
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repo: StockRepository) : ViewModel() {
    var stockItems: MutableLiveData<DataState<GetAllSummaryResponseModel>> = MutableLiveData(null);
    val stockArray: MutableLiveData<ArrayList<StockItem>> = MutableLiveData(null);
    private val handler = Handler(Looper.getMainLooper())
    val errorMessage: MutableLiveData<String> = MutableLiveData()

    init {
        getAllSummary()
        scheduleGetAllSummary()
    }


    private fun scheduleGetAllSummary() {
        handler.postDelayed({
            getAllSummary()
            // Schedule the next execution after 8 seconds
            scheduleGetAllSummary()
        }, 8000)
    }




    fun getAllSummary() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                withContext(Dispatchers.Main) {
                    repo.getAllStocks().collect {
                        stockItems.value = it
                        // counter.start()
                    }
                }
            }catch (e: Exception){
                val errorMessageText = when (e) {
                    is HttpException -> e.message()
                    else -> "An error occurred: ${e.message}"
                }
                errorMessage.postValue(errorMessageText)
            }
        }
    }

}


class Counter @Inject constructor(private val viewModel: MainViewModel) {
    fun start() {
        Timer().schedule(object : TimerTask() {
            override fun run() {
                viewModel.getAllSummary()
            }
        }, 0, 8000)
    }
}