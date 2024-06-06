package com.example.wavesoffood.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wavesoffood.R
import com.example.wavesoffood.adapter.MenuAdapter
import com.example.wavesoffood.databinding.FragmentSearchBinding


class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private lateinit var adapter:MenuAdapter
    private val originalMenuFoodName = listOf("Burger", "sandwich", "momo", "item", "sandwich", "momo")
    private val originalmenuItemPrice = listOf("$5", "$6", "$8", "$9", "$10", "$10")
    private val originalmenuImage = listOf(
        R.drawable.menu1,
        R.drawable.menu2,
        R.drawable.menu3,
        R.drawable.menu4,
        R.drawable.menu5,
        R.drawable.menu7
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    private val filterMenuFoodName = mutableListOf<String>()
    private val filterMenuItemPrice = mutableListOf<String>()
    private val filterMenuImage = mutableListOf<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSearchBinding.inflate(inflater,container,false)
       // adapter = MenuAdapter(filterMenuFoodName,filterMenuItemPrice,filterMenuImage, requireContext())
        binding.menuRecylclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.menuRecylclerView.adapter = adapter
        setupSearchView()
        showAllMenu()
        return binding.root
    }

    private fun showAllMenu() {
        filterMenuFoodName.clear()
        filterMenuItemPrice.clear()
        filterMenuImage.clear()

        filterMenuFoodName.addAll(originalMenuFoodName)
        filterMenuItemPrice.addAll(originalmenuItemPrice)
        filterMenuImage.addAll(originalmenuImage)
    }

    private fun setupSearchView() {
       binding.searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
           override fun onQueryTextSubmit(query: String): Boolean {
                filterMenuItem(query)
               return true
           }

           override fun onQueryTextChange(newText: String): Boolean {
                filterMenuItem(newText)
                return true
           }
       })
    }

    private fun filterMenuItem(query: String) {
        filterMenuFoodName.clear()
        filterMenuItemPrice.clear()
        filterMenuImage.clear()

        originalMenuFoodName.forEachIndexed{
            index, foodName ->
            if (foodName.contains(query,ignoreCase = true)){
                filterMenuFoodName.add(foodName)
                filterMenuItemPrice.add(originalmenuItemPrice[index])
                filterMenuImage.add(originalmenuImage[index])
            }
        }
        adapter.notifyDataSetChanged()
    }

    companion object {

    }
}