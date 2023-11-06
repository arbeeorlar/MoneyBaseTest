package com.app.moneybasetest.ui.screens.mainScreen

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.moneybasetest.data.model.GetAllSummaryResponseModel
import com.app.moneybasetest.data.model.StockItem
import com.app.moneybasetest.databinding.ActivityMainBinding
import com.app.moneybasetest.util.extensions.viewBinding
import com.app.moneybasetest.util.network.DataState.Success
import dagger.hilt.android.AndroidEntryPoint
import java.util.Timer
import java.util.TimerTask


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)
    var stockItems =  ArrayList<StockItem> ()
    private lateinit var mainAdapter: MainAdapter
    private var myTimer: Timer? = null
    private val viewModel: MainViewModel by viewModels<MainViewModel>()
   // val artistDetailViewModel = hiltViewModel<ArtistDetailViewModel>()

    //private val moviesAdapter = MovieListAdapter(emptyList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        myTimer = Timer()
        myTimer!!.schedule(object : TimerTask() {
            override fun run() {
                viewModel.getAllSummary()
            }
        }, 0, 8000) //updating it evry 8seconds
        viewModel.getAllSummary()
        setupUI()
        setupObserver()

    }

    private fun setupUI() {
        binding.viewRecyclerStockList.layoutManager = LinearLayoutManager(this)
        // recyclerView.layoutManager =
        mainAdapter = applicationContext?.let {
            MainAdapter(it, resources, arrayListOf()) {
                //// move to the next page
            }
        }!!
        binding.viewRecyclerStockList.adapter = mainAdapter
    }





    @SuppressLint("NotifyDataSetChanged")
    private fun renderList(items: ArrayList<StockItem>) {
        mainAdapter.addData(items)
        mainAdapter.notifyDataSetChanged()
    }
    private fun setupObserver() {
        viewModel.stockItems.observe(this, Observer { it ->
            if (it is Success<GetAllSummaryResponseModel>) {
                it.data.marketSummaryAndSparkResponse.result.forEach { data ->
                    stockItems.add(data)
                }
                renderList(stockItems)
            }
        })
    }

}