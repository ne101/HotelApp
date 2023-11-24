package com.example.hotelapp.presentation.fragments

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.view.animation.OvershootInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.hotelapp.R
import com.example.hotelapp.databinding.FragmentRoomsBinding
import com.example.hotelapp.databinding.RoomCardBinding
import com.example.hotelapp.presentation.HotelApp
import com.example.hotelapp.presentation.ViewModelFactory
import com.example.hotelapp.presentation.viewModels.RoomsViewModel
import javax.inject.Inject

class RoomsFragment : Fragment() {

    private val args by navArgs<RoomsFragmentArgs>()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var roomsViewModel: RoomsViewModel

    private val component by lazy {
        (requireActivity().application as HotelApp).component
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }

    private var _binding: FragmentRoomsBinding? = null
    private val binding: FragmentRoomsBinding
        get() = _binding ?: throw RuntimeException(" FragmentRoomsBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRoomsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        roomsViewModel = ViewModelProvider(this, viewModelFactory)[RoomsViewModel::class.java]
        animationButtonBack()
        showRooms()
        binding.customToolbar.tvScreenName.text = args.hotelName
        binding.customToolbar.ivBackScreen.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showRooms() {
        roomsViewModel.rooms.observe(viewLifecycleOwner) {
            for (room in it.rooms) {
                val roomBinding = RoomCardBinding.inflate(layoutInflater, binding.llroom, false)
                binding.llroom.addView(roomBinding.root)
                val imageList = ArrayList<SlideModel>()
                for (image in room.image_urls) {
                    imageList.add(SlideModel(image))
                }
                roomBinding.imageSlider.setImageList(imageList, ScaleTypes.FIT)
                roomBinding.tvNameRoom.text = room.name
                roomBinding.tvMinimalPrice.text =
                    getString(R.string.Price, room.price).replace(",", " ")
                roomBinding.tvPriceForIt.text = room.price_per
                roomBinding.tvPeculiarities1.text = room.peculiarities[0]
                roomBinding.tvPeculiarities2.text = room.peculiarities[1]

                roomBinding.buttonSelectRoom.setOnClickListener {
                    findNavController().navigate(R.id.action_roomsFragment_to_bookingFragment)
                }
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun animationButtonBack() {
        binding.customToolbar.ivBackScreen.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    // Анимация уменьшения при нажатии
                    ObjectAnimator.ofPropertyValuesHolder(
                        v,
                        PropertyValuesHolder.ofFloat("scaleX", 0.9f),
                        PropertyValuesHolder.ofFloat("scaleY", 0.9f)
                    ).apply {
                        duration = 200
                        interpolator = DecelerateInterpolator()
                        start()
                    }
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    // Анимация возвращения к исходному размеру при отпускании
                    ObjectAnimator.ofPropertyValuesHolder(
                        v,
                        PropertyValuesHolder.ofFloat("scaleX", 1f),
                        PropertyValuesHolder.ofFloat("scaleY", 1f)
                    ).apply {
                        duration = 200
                        interpolator = OvershootInterpolator()
                        start()
                    }
                }
            }
            false
        }
    }
}