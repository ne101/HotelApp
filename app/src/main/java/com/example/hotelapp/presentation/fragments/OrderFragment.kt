package com.example.hotelapp.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.hotelapp.R
import com.example.hotelapp.databinding.FragmentOrderBinding


class OrderFragment : Fragment() {
    private var _binding: FragmentOrderBinding? = null
    private val binding: FragmentOrderBinding
        get() = _binding ?: throw RuntimeException(" FragmentOrderBinding == null")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentOrderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.customToolbar.tvScreenName.text = "Заказ оплачен"
        binding.customToolbar.ivBackScreen.setOnClickListener {
            findNavController().navigate(R.id.action_orderFragment_to_bookingFragment)
        }
        binding.tvOrder.text = getString(R.string.order, (1000..100000).random())
        binding.button.setOnClickListener {
            findNavController().navigate(R.id.action_orderFragment_to_hotelFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}