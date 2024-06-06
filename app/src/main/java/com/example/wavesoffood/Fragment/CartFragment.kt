package com.example.wavesoffood.Fragment

import android.content.ClipDescription
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wavesoffood.CongratsBottomSheet
import com.example.wavesoffood.PayOutActivity
import com.example.wavesoffood.R
import com.example.wavesoffood.adapter.CartAdapter
import com.example.wavesoffood.databinding.FragmentCartBinding
import com.example.wavesoffood.model.CartItems
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class CartFragment : Fragment() {
    private lateinit var binding: FragmentCartBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var foodNames : MutableList<String>
    private lateinit var foodPrices : MutableList<String>
    private lateinit var foodDescription : MutableList<String>
    private lateinit var foodImagesUri : MutableList<String>
    private lateinit var foodIngredients : MutableList<String>
    private lateinit var quantity : MutableList<Int>
    private lateinit var cartAdapter: CartAdapter
    private lateinit var userId : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater, container, false)

        auth = FirebaseAuth.getInstance()
        retrieveCartItems()



        binding.proceedButton.setOnClickListener {
            val intent = Intent(requireContext(), PayOutActivity::class.java)
            startActivity(intent)
        }


            return binding.root
    }

    private fun retrieveCartItems() {


        //database reference to the Firebase
        database = FirebaseDatabase.getInstance()
        userId = auth.currentUser?.uid?:""
        val foodReference : DatabaseReference = database.reference.child("user").child(userId).child("CartItems")

        //list to store cart items
        foodNames = mutableListOf()
        foodPrices = mutableListOf()
        foodDescription = mutableListOf()
        foodImagesUri = mutableListOf()
        foodIngredients = mutableListOf()
        quantity = mutableListOf()

        //fetch data from database
        foodReference.addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
               for (foodSnapshot in snapshot.children){
                   // get the cartItems object from the children node
                   val cartItems = foodSnapshot.getValue(CartItems::class.java)

                   //add cartItems details to the list
                   cartItems?.foodName?.let { foodNames.add(it) }
                   cartItems?.foodPrice?.let { foodPrices.add(it) }
                   cartItems?.foodDescription?.let { foodDescription.add(it) }
                   cartItems?.foodImage?.let { foodImagesUri.add(it) }
                   cartItems?.foodQuantity?.let { quantity.add(it) }
                   cartItems?.foodIngredint?.let { foodIngredients.add(it) }

               }
                setAdapter()
            }

            private fun setAdapter() {
                val adapter = CartAdapter(requireContext(), foodNames, foodPrices, foodDescription,foodImagesUri, quantity, foodIngredients)
                binding.cartRecyclerView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
                binding.cartRecyclerView.adapter = adapter
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "data not fetch", Toast.LENGTH_SHORT).show()
            }
        })
    }

    companion object {

    }
}