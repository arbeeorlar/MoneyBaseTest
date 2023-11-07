package com.app.moneybasetest.ui.screens.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.moneybasetest.data.model.GetAllSummaryResponseModel
import com.app.moneybasetest.data.model.GetItemSummaryResponseModel
import com.app.moneybasetest.data.model.StockItem
import com.app.moneybasetest.data.repository.StockRepository
import com.app.moneybasetest.util.network.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject



@HiltViewModel
class StockDetailViewModel @Inject constructor(private val repo: StockRepository) : ViewModel() {
    var stockObjectItems: MutableLiveData<DataState<GetItemSummaryResponseModel>> = MutableLiveData(null);

    var stockSymbol: MutableLiveData<String>  =  MutableLiveData("")



    fun getStockItem( stockSymbol : String ) {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                repo.getStockDetail(stockSymbol).collect{
                    stockObjectItems.postValue(it)
                }
            }
        }
    }


}