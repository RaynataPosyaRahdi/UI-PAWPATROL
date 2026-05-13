package com.example.pawpatrol.ui.grooming

import androidx.lifecycle.*
import com.example.pawpatrol.repository.GroomingRepository
import com.example.pawpatrol.models.grooming.GroomingBooking
import com.example.pawpatrol.models.grooming.GroomingBookingRequest
import com.example.pawpatrol.models.grooming.GroomingServices
import kotlinx.coroutines.launch

class GroomingViewModel(
    private val repository: GroomingRepository
) : ViewModel() {

    private val _services =
        MutableLiveData<List<GroomingServices>>()

    val services:
            LiveData<List<GroomingServices>>
            = _services

    private val _bookingResult =
        MutableLiveData<GroomingBooking>()

    val bookingResult:
            LiveData<GroomingBooking>
            = _bookingResult

    fun getServices() {

        viewModelScope.launch {

            val response =
                repository.getServices()

            if (response.isSuccessful) {

                _services.value =
                    response.body()

            }
        }
    }

    fun createBooking(
        request: GroomingBookingRequest
    ) {

        viewModelScope.launch {

            val response =
                repository.createBooking(
                    request
                )

            if (response.isSuccessful) {

                _bookingResult.value =
                    response.body()

            }
        }
    }
}