package com.app.moneybasetest.ui.screens.mainScreen

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.moneybasetest.data.model.GetAllSummaryResponseModel
import com.app.moneybasetest.data.model.StockItem
import com.app.moneybasetest.databinding.ActivityMainBinding
import com.app.moneybasetest.ui.screens.detail.StockDetailActivity
import com.app.moneybasetest.util.extensions.viewBinding
import com.app.moneybasetest.util.network.DataState
import com.app.moneybasetest.util.network.DataState.Success
import dagger.hilt.android.AndroidEntryPoint
import java.util.Timer
import java.util.TimerTask


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)
    var stockItems =  ArrayList<StockItem> ()
    var filteredStockItems =  ArrayList<StockItem> ()
    private lateinit var mainAdapter: MainAdapter
    private val viewModel: MainViewModel by viewModels<MainViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupUI()
        setupObserver()
        searchModule()

    }



    override fun onResume() {
        super.onResume()
    }

    private fun setupUI() {
        binding.viewRecyclerStockList.layoutManager = LinearLayoutManager(this)
        // recyclerView.layoutManager =
        mainAdapter = applicationContext?.let {
            MainAdapter(it, resources, arrayListOf()) { data ->
                //// move to the next page
                startActivity(StockDetailActivity.newIntent(this@MainActivity,data))
            }
        }!!
        binding.viewRecyclerStockList.adapter = mainAdapter
    }

    private fun searchModule(){
        binding.searchText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                filteredStockItems.clear()
                stockItems
                    .filter {
                        (it.exchange.lowercase().contains(s.toString()) || it.symbol.lowercase().contains(s.toString()) )
                    }
                    .forEach {

                        filteredStockItems.add(it)
                    }
                Log.d("Filtered Bank", filteredStockItems.size.toString())
                clearData()
                renderList(filteredStockItems)
            }

            override fun afterTextChanged(s: Editable) {}
        });
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun clearData() {
        mainAdapter.clearData()
        mainAdapter.notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun renderList(items: ArrayList<StockItem>) {
        mainAdapter.addData(items)
        mainAdapter.notifyDataSetChanged()
    }
    private fun setupObserver() {
        viewModel.stockItems.observe(this) { dataState ->
            when (dataState) {
                is Success -> {
                    dataState.data.marketSummaryAndSparkResponse.result.forEach { data ->
                        stockItems.add(data)
                    }
                    viewModel.stockArray.postValue(stockItems)
                    binding.viewRecyclerStockList.visibility = View.VISIBLE
                    binding.progressBar.visibility =  View.GONE
                    renderList(stockItems)
                }
                is DataState.Error -> {

                    val errorMessage = dataState.exception.message
                    Toast.makeText(applicationContext,errorMessage,Toast.LENGTH_LONG).show()

                }
                is DataState.Loading -> {
                    binding.viewRecyclerStockList.visibility = View.GONE
                    binding.progressBar.visibility =  View.VISIBLE
                    // Handle loading state, e.g., show a progress indicator
                }
            }
        }
    }

}