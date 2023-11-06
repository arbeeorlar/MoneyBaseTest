package com.app.moneybasetest.ui.screens.mainScreen

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.moneybasetest.R
import com.app.moneybasetest.data.model.GetAllSummaryResponseModel
import com.app.moneybasetest.data.model.StockItem
import com.app.moneybasetest.databinding.ItemStockListLayoutBinding




class MainAdapter(val context: Context,
                   val resources: Resources,
                   private val resultList: ArrayList<StockItem>,
                   private var onItemClicked: (bankItem: StockItem) -> Unit) : RecyclerView.Adapter<MainAdapter.MyViewHolder>() {

    var resultFilterList = ArrayList<StockItem>()

    init {
        resultFilterList = resultList
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindItems(resultFilterList[position])
        holder.itemView.setOnClickListener {
            onItemClicked(resultFilterList[position])
        }
    }

    override fun getItemCount(): Int {
        return resultFilterList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_stock_list_layout, parent, false)
        return MyViewHolder(view)
    }


    fun addData(list: ArrayList<StockItem>) {
        resultFilterList.addAll(list)
        notifyDataSetChanged()
    }

    fun clearData() {
        resultList.clear()
        resultFilterList.clear()
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(item: StockItem) {
            itemView.findViewById<TextView>(R.id.view_text_icon).text = item.fullExchangeName.first().toString()
            itemView.findViewById<TextView>(R.id.view_stock_title_text).text = item.fullExchangeName
            itemView.findViewById<TextView>(R.id.view_stock_subtitle_text).text = item.symbol
        }
    }

}




