package com.example.hotelapp.presentation.fragments

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.view.animation.OvershootInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.hotelapp.R
import com.example.hotelapp.databinding.FragmentHotelBinding
import com.example.hotelapp.databinding.HotelBinding
import com.example.hotelapp.databinding.HotelInfoCardBinding
import com.example.hotelapp.databinding.MyActionBarBinding
import com.example.hotelapp.domain.entity.hotel.HotelEntity
import com.example.hotelapp.presentation.HotelApp
import com.example.hotelapp.presentation.viewModels.HotelViewModel
import com.example.hotelapp.presentation.ViewModelFactory
import javax.inject.Inject

class HotelFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var hotelViewModel: HotelViewModel

    private val component by lazy {
        (requireActivity().application as HotelApp).component
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }

    private var _binding: FragmentHotelBinding? = null
    private val binding: FragmentHotelBinding
        get() = _binding ?: throw RuntimeException("FragmentHotelBinding==null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHotelBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hotelViewModel = ViewModelProvider(this, viewModelFactory)[HotelViewModel::class.java]
        showHotelInfo()
        binding.customToolbar.tvScreenName.text = "Отель"
        binding.customToolbar.ivBackScreen.visibility = View.INVISIBLE
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showHotelInfo() {
        hotelViewModel.hotelInfo.observe(viewLifecycleOwner) {
            val imageList = ArrayList<SlideModel>()
            val hotelBinding = HotelBinding.inflate(layoutInflater, binding.llHotel, false)
            binding.llHotel.addView(hotelBinding.root)
            for (image in it.image_urls) {
                imageList.add(SlideModel(image))
            }
            hotelBinding.imageSlider.setImageList(imageList, ScaleTypes.FIT)
            hotelBinding.tvName.text = it.name
            hotelBinding.tvAdress.text = it.adress
            hotelBinding.tvMinimalPrice.text =
                getString(R.string.minimalPrice, it.minimal_price).replace(","," ")
            hotelBinding.tvPriceForIt.text = it.price_for_it
            hotelBinding.tvRating.text = it.rating.toString()
            hotelBinding.tvRatingName.text = it.rating_name
            val hotelInfoBinding = HotelInfoCardBinding.inflate(layoutInflater, binding.llHotel, false)
            binding.llHotel.addView(hotelInfoBinding.root)
            hotelInfoBinding.tvDescription.text = it.about_the_hotel.description
            hotelInfoBinding.tvInfo1.text = it.about_the_hotel.peculiarities[0]
            hotelInfoBinding.tvInfo2.text = it.about_the_hotel.peculiarities[1]
            hotelInfoBinding.tvInfo3.text = it.about_the_hotel.peculiarities[2]
            hotelInfoBinding.tvInfo4.text = it.about_the_hotel.peculiarities[3]
            showNextScreen(it)
        }
    }
    private fun showNextScreen(hotel: HotelEntity) {
        binding.button.setOnClickListener {
            findNavController().navigate(HotelFragmentDirections.actionHotelFragmentToRoomsFragment(hotel.name))
        }
    }



}