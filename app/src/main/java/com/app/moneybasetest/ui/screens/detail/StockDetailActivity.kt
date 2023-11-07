package com.app.moneybasetest.ui.screens.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.app.moneybasetest.data.model.GetAllSummaryResponseModel
import com.app.moneybasetest.data.model.GetItemSummaryResponseModel
import com.app.moneybasetest.data.model.StockItem
import com.app.moneybasetest.databinding.ActivityStockDetailBinding
import com.app.moneybasetest.ui.screens.mainScreen.MainViewModel
import com.app.moneybasetest.util.extensions.viewBinding
import com.app.moneybasetest.util.network.DataState
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class StockDetailActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityStockDetailBinding::inflate)
    private val viewModel: StockDetailViewModel by viewModels<StockDetailViewModel>()

    companion object {
        private const val EXTRA_STOCK_SYMBOL = "extra_stock_symbol"
        fun newIntent(context: Context, stockItem: StockItem): Intent = Intent(context, StockDetailActivity::class.java)
            .putExtra(EXTRA_STOCK_SYMBOL, stockItem)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        var stockObject =  intent.getSerializableExtra(EXTRA_STOCK_SYMBOL) as StockItem
        viewModel.getStockItem(stockObject.symbol)


        binding.viewStockDetailTopTitle.text = stockObject.fullExchangeName
        binding.viewStockDetailTopSymbol.text = stockObject.symbol
        binding.viewStockName.text = stockObject.fullExchangeName
        binding.viewStockInformation.text = stockObject.market
        binding.viewStockDetailToolbar.setOnClickListener { finish() }
        setupObserver()



    }

    private fun renderUI(item : GetItemSummaryResponseModel){

        binding.firstLabel.text = "Price : "
        binding.firstValue.text = item.price.quoteSourceName


    }

    private fun setupObserver() {
        viewModel.stockObjectItems.observe(this, Observer { it ->
            if (it is DataState.Success<GetItemSummaryResponseModel>) {
                renderUI(it.data)
            }
        })
    }

}