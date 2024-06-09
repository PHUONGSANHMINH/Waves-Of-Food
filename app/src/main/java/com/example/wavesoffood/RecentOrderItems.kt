package com.example.wavesoffood

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wavesoffood.adapter.RecentBuyAdapter
import com.example.wavesoffood.databinding.ActivityRecentOrderItemsBinding
import com.example.wavesoffood.model.OrderDetails

class RecentOrderItems : AppCompatActivity() {

    private val binding: ActivityRecentOrderItemsBinding by lazy {
        ActivityRecentOrderItemsBinding.inflate(layoutInflater)
    }
    private lateinit var allFoodNames: ArrayList<String>
    private lateinit var allFoodImages: ArrayList<String>
    private lateinit var allFoodPrices: ArrayList<String>
    private lateinit var allFoodQuantities: ArrayList<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            finish()
        }

        val recentOrderItems = intent.getSerializableExtra("RecentBuyOrderItem") as? ArrayList<OrderDetails>
        recentOrderItems?.let { orderDetails ->
            if (orderDetails.isNotEmpty()) {
                val recentOrderItem: OrderDetails = orderDetails[0]

                allFoodNames = ArrayList(recentOrderItem.foodNames ?: listOf())
                allFoodImages = ArrayList(recentOrderItem.foodImages ?: listOf())
                allFoodPrices = ArrayList(recentOrderItem.foodPrices ?: listOf())
                allFoodQuantities = ArrayList(recentOrderItem.foodQuantities ?: listOf())
            }
        } ?: run {
            // Xử lý trường hợp không có dữ liệu hoặc dữ liệu không đúng kiểu
            allFoodNames = arrayListOf()
            allFoodImages = arrayListOf()
            allFoodPrices = arrayListOf()
            allFoodQuantities = arrayListOf()
        }

        setAdapter()
    }

    private fun setAdapter() {
        val rv = binding.recyclerViewRecentBuy
        rv.layoutManager = LinearLayoutManager(this)

        val adapter = RecentBuyAdapter(
            this,
            allFoodNames,
            allFoodImages,
            allFoodPrices,
            allFoodQuantities
        )

        rv.adapter = adapter
    }
}
