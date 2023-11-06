package com.app.moneybasetest.ui.screens.mainScreen

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
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repo: StockRepository) : ViewModel() {
    var stockItems: MutableLiveData<DataState<GetAllSummaryResponseModel>> = MutableLiveData(null);
    val stockItemList: MutableLiveData<List<StockItem>> = MutableLiveData(emptyList());

    fun getAllSummary() {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                repo.getAllStocks().collect{
                    stockItems.value =  it
                }
            }
        }
    }






}