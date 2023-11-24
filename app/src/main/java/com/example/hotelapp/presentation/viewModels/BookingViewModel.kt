package com.example.hotelapp.presentation.viewModels

import android.content.res.ColorStateList
import android.graphics.Color
import android.widget.EditText
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hotelapp.databinding.TouristInformationCardBinding
import com.example.hotelapp.domain.entity.booking.BookingEntity
import com.example.hotelapp.domain.usecase.GetBookingInfoUseCase
import javax.inject.Inject

class BookingViewModel @Inject constructor(
    private val getBookingInfoUseCase: GetBookingInfoUseCase
) : ViewModel() {
    private val _booking = getBookingInfoUseCase.invoke()
    val booking: LiveData<BookingEntity>
        get() = _booking

    private val _isFormValid = MutableLiveData<Boolean>()
    val isFormValid: LiveData<Boolean>
        get() = _isFormValid

    private val _isEmailValid = MutableLiveData<Boolean>()
    val isEmailValid: LiveData<Boolean>
        get() = _isEmailValid

    private val _isPhoneNumberValid = MutableLiveData<Boolean>()
    val isPhoneNumberValid: LiveData<Boolean>
        get() = _isPhoneNumberValid


    val isAllValid = MediatorLiveData<Boolean>().apply {
        addSource(_isFormValid) { value = checkAllValid() }
        addSource(_isEmailValid) { value = checkAllValid() }
        addSource(_isPhoneNumberValid) { value = checkAllValid() }
    }

    private fun checkAllValid() = _isFormValid.value == true && _isEmailValid.value == true && _isPhoneNumberValid.value == true

    fun setEmailValid(isValid: Boolean) {
        _isEmailValid.value = isValid
    }

    fun setPhoneNumberValid(isValid: Boolean) {
        _isPhoneNumberValid.value = isValid
    }

    fun validateForm(touristBindings: List<TouristInformationCardBinding>) {
        var allFieldsFilled = true
        for (touristBinding in touristBindings) {
            val editTexts = listOf(
                touristBinding.validityPeriodEditText,
                touristBinding.passportNumberEditText,
                touristBinding.surNameEditText,
                touristBinding.bdEditText,
                touristBinding.nameEditText,
                touristBinding.citizenShipEditText
            )
            for (editText in editTexts) {
                if (editText.text.toString().trim().isEmpty()) {
                    allFieldsFilled = false
                    editText.backgroundTintList =
                        ColorStateList.valueOf(Color.parseColor("#26EB5757"))
                } else {
                    editText.backgroundTintList =
                        ColorStateList.valueOf(Color.parseColor("#F6F6F9"))
                }
            }
            if (!allFieldsFilled) break
        }
        _isFormValid.value = allFieldsFilled
    }


}

