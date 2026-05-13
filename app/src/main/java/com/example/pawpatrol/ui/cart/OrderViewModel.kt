package com.example.pawpatrol.ui.cart

import androidx.lifecycle.*
import com.example.pawpatrol.repository.OrderRepository
import com.example.pawpatrol.models.order.Orders
import kotlinx.coroutines.launch

class OrderViewModel(
    private val repository: OrderRepository
) : ViewModel() {

    private val _orders =
        MutableLiveData<List<Orders>>()

    val orders:
            LiveData<List<Orders>>
            = _orders

    fun getOrders() {

        viewModelScope.launch {

            val response =
                repository.getOrders()

            if (response.isSuccessful) {

                _orders.value =
                    response.body()

            }
        }
    }
}