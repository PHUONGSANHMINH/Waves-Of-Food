package com.example.wavesoffood

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wavesoffood.adapter.NotificationAdapter
import com.example.wavesoffood.databinding.FragmentNotifactionBottomBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class Notifaction_Bottom_Fragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentNotifactionBottomBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNotifactionBottomBinding.inflate(layoutInflater, container, false)
        val notifications = listOf("Your order has been Canceled Successfully", "Order has been taken by the driver", "Congrats Your Order Placed")
        val notificationImage = listOf(R.drawable.sademoji, R.drawable.truck, R.drawable.correct)
        val adapter = NotificationAdapter(
            ArrayList(notifications),
            ArrayList(notificationImage)
        )
        return binding.root
    }

    companion object {

    }
}