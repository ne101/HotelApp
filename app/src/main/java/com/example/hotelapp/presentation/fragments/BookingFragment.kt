package com.example.hotelapp.presentation.fragments

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.view.animation.OvershootInterpolator
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.hotelapp.R
import com.example.hotelapp.databinding.AddTouristBinding
import com.example.hotelapp.databinding.BookingCard3rdScreenBinding
import com.example.hotelapp.databinding.FragmentBookingBinding
import com.example.hotelapp.databinding.HoteInfoAbout3rdScreenCardBinding
import com.example.hotelapp.databinding.InfoBuyerCardBinding
import com.example.hotelapp.databinding.PriceCardBinding
import com.example.hotelapp.databinding.TouristInformationCardBinding
import com.example.hotelapp.presentation.HotelApp
import com.example.hotelapp.presentation.ViewModelFactory
import com.example.hotelapp.presentation.viewModels.BookingViewModel
import com.example.hotelapp.presentation.viewModels.HotelViewModel
import javax.inject.Inject

class BookingFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var bookingViewModel: BookingViewModel

    private val component by lazy {
        (requireActivity().application as HotelApp).component
    }
    private val touristBindings = mutableListOf<TouristInformationCardBinding>()



    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }

    private var _binding: FragmentBookingBinding? = null
    private val binding: FragmentBookingBinding
        get() = _binding ?: throw RuntimeException("FragmentBookingBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBookingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bookingViewModel = ViewModelProvider(this, viewModelFactory)[BookingViewModel::class.java]
        animationButtonBack()
        showBooking()
        addTextChangeListeners()
        showTourists()
        showAddTourists()
        launchFinalScreen()

        binding.customToolbar.tvScreenName.text = "Бронирование"
        binding.customToolbar.ivBackScreen.setOnClickListener {
            findNavController().navigateUp()
        }

        bookingViewModel.isAllValid.observe(viewLifecycleOwner) { isAllValid ->
            if (isAllValid) {
                val navOptions = NavOptions.Builder()
                    .setPopUpTo(R.id.bookingFragment, true)
                    .build()
                findNavController().navigate(R.id.action_bookingFragment_to_orderFragment, null, navOptions)
            } else if (bookingViewModel.isEmailValid.value == false || bookingViewModel.isFormValid.value == false ) {
                Toast.makeText(context, "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show()

            }
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showBooking() {
        val hotelInfoBinding = HoteInfoAbout3rdScreenCardBinding.inflate(
            layoutInflater, binding.llBooking, false
        )
        binding.llBooking.addView(hotelInfoBinding.root)

        val bookingBinding = BookingCard3rdScreenBinding.inflate(
            layoutInflater, binding.llBooking, false
        )
        binding.llBooking.addView(bookingBinding.root)

        bookingViewModel.booking.observe(viewLifecycleOwner) {
            hotelInfoBinding.tvName.text = it.hotel_name
            hotelInfoBinding.tvAdress.text = it.hotel_adress
            hotelInfoBinding.tvRating.text = it.horating.toString()
            hotelInfoBinding.tvRatingName.text = it.rating_name
            bookingBinding.tvDeparture.text = it.departure
            bookingBinding.tvRoom.text = it.room
            bookingBinding.tvArrivalCountry.text = it.arrival_country
            bookingBinding.tvHotelName.text = it.hotel_name
            bookingBinding.tvUtrition.text = it.nutrition
            bookingBinding.tvTourDate.text = getString(
                R.string.date, it.tour_date_start, it.tour_date_stop
            )
            showResultPrice(bookingViewModel)
        }
    }

    private fun showTourists() {
        val touristBinding =
            TouristInformationCardBinding.inflate(
                layoutInflater, binding.llBooking, false
            )
        binding.llBooking.addView(touristBinding.root)
        hideInfo(touristBinding)
        touristBindings.add(touristBinding)
        addTextValidationListener(touristBinding)
    }

    private fun showAddTourists() {
        var countTourist = 0
        val list = listOf<String>(
            "Первый",
            "Второй",
            "Третий",
            "Четвертый",
            "Пятый",
            "Шестой",
            "Седьмой",
            "Восьмой",
            "Девятый",
            "Десятый"
        )
        val addTourist = AddTouristBinding.inflate(
            layoutInflater, binding.llBooking, false
        )
        binding.llBooking.addView(addTourist.root)
        addTourist.ivAddTouristButton.setOnClickListener {
            val touristBinding = TouristInformationCardBinding.inflate(
                layoutInflater, binding.llBooking, false
            )
            if (countTourist != list.size - 1) {
                binding.llBooking.addView(touristBinding.root, binding.llBooking.childCount - 2)
                touristBinding.tvCountTourists.text = "${list[countTourist + 1]} Турист"
                countTourist++
            }
            hideInfo(touristBinding)
            touristBindings.add(touristBinding)
            addTextValidationListener(touristBinding)
        }
    }


    private fun launchFinalScreen() {
        binding.button.setOnClickListener {
            bookingViewModel.validateForm(touristBindings)
        }
    }


    private fun hideInfo(touristBinding: TouristInformationCardBinding) {
        touristBinding.cardViewOff.setOnClickListener {
            touristBinding.cardViewBirthDay.visibility = View.VISIBLE
            touristBinding.cardViewName.visibility = View.VISIBLE
            touristBinding.cardViewCitizenShip.visibility = View.VISIBLE
            touristBinding.cardViewSurName.visibility = View.VISIBLE
            touristBinding.cardViewPassportNumber.visibility = View.VISIBLE
            touristBinding.cardViewValidityPeriod.visibility = View.VISIBLE
            touristBinding.cardViewOn.visibility = View.VISIBLE
            touristBinding.cardViewOff.visibility = View.INVISIBLE
        }
        touristBinding.cardViewOn.setOnClickListener {
            touristBinding.cardViewBirthDay.visibility = View.GONE
            touristBinding.cardViewName.visibility = View.GONE
            touristBinding.cardViewCitizenShip.visibility = View.GONE
            touristBinding.cardViewSurName.visibility = View.GONE
            touristBinding.cardViewPassportNumber.visibility = View.GONE
            touristBinding.cardViewValidityPeriod.visibility = View.GONE
            touristBinding.cardViewOn.visibility = View.INVISIBLE
            touristBinding.cardViewOff.visibility = View.VISIBLE
        }
    }

    private fun showResultPrice(viewModel: BookingViewModel) {

        val priceCardBinding = PriceCardBinding.inflate(
            layoutInflater, binding.llBooking, false
        )
        viewModel.booking.observe(viewLifecycleOwner) {
            binding.llBooking.addView(priceCardBinding.root)
            priceCardBinding.tvTourPrice.text =
                getString(R.string.Price, it.tour_price).replace(",", " ")
            priceCardBinding.tvFuelCharge.text =
                getString(R.string.Price, it.fuel_charge).replace(",", " ")
            priceCardBinding.tvServiceCharge.text =
                getString(R.string.Price, it.service_charge).replace(",", " ")
            val price = it.tour_price + it.fuel_charge + it.service_charge
            priceCardBinding.tvResultPrice.text =
                getString(R.string.Price, price).replace(",", " ")
            binding.button.text = getString(R.string.Price, price).replace(",", " ")
        }
    }

    private fun addTextValidationListener(touristBinding: TouristInformationCardBinding) {
        val editTexts = listOf(
            touristBinding.validityPeriodEditText,
            touristBinding.passportNumberEditText,
            touristBinding.surNameEditText,
            touristBinding.bdEditText,
            touristBinding.nameEditText,
            touristBinding.citizenShipEditText
        )
        for (editText in editTexts) {
            editText.setOnFocusChangeListener { _, hasFocus ->
                if (!hasFocus) {
                    val text = editText.text.toString()
                    if (text.trim().isEmpty()) {
                        editText.backgroundTintList =
                            ColorStateList.valueOf(Color.parseColor("#26EB5757"))
                    } else {
                        editText.backgroundTintList =
                            ColorStateList.valueOf(Color.parseColor("#F6F6F9"))

                    }
                }
            }
        }
    }


    private fun addEmailValidationListener(infoBuyerBinding: InfoBuyerCardBinding) {
        infoBuyerBinding.emailEditText.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val email = infoBuyerBinding.emailEditText.text.toString()
                val isValidEmail = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()

                if (isValidEmail) {
                    infoBuyerBinding.emailEditText.backgroundTintList =
                        ColorStateList.valueOf(Color.parseColor("#F6F6F9"))
                    bookingViewModel.setEmailValid(true)
                } else {
                    infoBuyerBinding.emailEditText.backgroundTintList =
                        ColorStateList.valueOf(Color.parseColor("#26EB5757"))
                    bookingViewModel.setEmailValid(false)
                }
            }
        }
    }


    private fun addTextChangeListeners() {
        val infoBuyerBinding = InfoBuyerCardBinding.inflate(
            layoutInflater, binding.llBooking, false
        )
        binding.llBooking.addView(infoBuyerBinding.root)

        addEmailValidationListener(infoBuyerBinding)

        infoBuyerBinding.phoneEditText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus && infoBuyerBinding.phoneEditText.text?.isEmpty() == true) {
                infoBuyerBinding.phoneEditText.setText("+7 ")
                infoBuyerBinding.phoneEditText.setSelection(
                    infoBuyerBinding.phoneEditText.text?.length ?: 0
                )
            }

        }

        infoBuyerBinding.phoneEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }


            override fun afterTextChanged(s: Editable?) {
                val mask = "(***) ***-**-**"
                val unmaskedText = s.toString().replace(Regex("[^\\d]"), "").drop(1)
                var maskedText = "+7 "
                var unmaskedIndex = 0

                for (char in mask) {
                    if (char == '*') {
                        if (unmaskedIndex < unmaskedText.length) {
                            maskedText += unmaskedText[unmaskedIndex]
                            unmaskedIndex++
                        } else {
                            maskedText += char
                        }
                    } else {
                        maskedText += char
                    }
                }

                infoBuyerBinding.phoneEditText.removeTextChangedListener(this)
                infoBuyerBinding.phoneEditText.setText(maskedText)
                val nextPosition = maskedText.indexOfFirst { it == '*' }
                if (nextPosition != -1) {
                    infoBuyerBinding.phoneEditText.setSelection(nextPosition)
                } else {
                    infoBuyerBinding.phoneEditText.setSelection(maskedText.length)
                }
                infoBuyerBinding.phoneEditText.addTextChangedListener(this)

                val containsStar = maskedText.contains('*')
                if (containsStar) {
                    infoBuyerBinding.phoneEditText.backgroundTintList =
                        ColorStateList.valueOf(Color.parseColor("#26EB5757"))
                    bookingViewModel.setPhoneNumberValid(false)
                } else {
                    infoBuyerBinding.phoneEditText.backgroundTintList =
                        ColorStateList.valueOf(Color.parseColor("#F6F6F9"))
                    bookingViewModel.setPhoneNumberValid(true)
                }
            }
        })

    }


    @SuppressLint("ClickableViewAccessibility")
    private fun animationButtonBack() {
        binding.customToolbar.ivBackScreen.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
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