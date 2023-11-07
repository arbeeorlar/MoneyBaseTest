package com.app.moneybasetest.ui.screens.mainScreen

import android.os.CountDownTimer
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
import java.util.Timer
import java.util.TimerTask
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repo: StockRepository) : ViewModel() {
    var stockItems: MutableLiveData<DataState<GetAllSummaryResponseModel>> = MutableLiveData(null);
    val stockArray: MutableLiveData<ArrayList<StockItem>> = MutableLiveData(null);
    val counter = Counter(this)
    init {
        getAllSummary()
    }




    fun getAllSummary() {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                repo.getAllStocks().collect{
                    stockItems.value =  it

                    //uncommenting the counter
                   // will work but the Yahoo finacnce
                // subscription is on basic
                   // counter.start()
                }
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